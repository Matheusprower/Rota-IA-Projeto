package com.rota.app.bdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.rota.app.model.Rota;
import com.rota.app.model.Usuario;
import com.rota.app.repository.RotaRepository;
import com.rota.app.repository.UsuarioRepository;
import com.rota.app.service.ApiService;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

@CucumberContextConfiguration
@SpringBootTest
@Transactional
public class RotasEUsuariosSteps {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RotaRepository rotaRepository;

    private ApiService apiService = new ApiService(WebClient.builder());

    private List<String> enderecosParaOtimizar;
    private String respostaOtimizacao;
    private Usuario usuarioAtual;
    private Rota rotaAtual;
    private List<Rota> historicoResultado;

    // --- CENÁRIO 1 ---
    @Dado("que eu tenho uma lista de {int} endereços para visitar")
    public void que_eu_tenho_uma_lista_de_enderecos_para_visitar(Integer qtd) {
        if (qtd == 3) {
            enderecosParaOtimizar = Arrays.asList("Endereço A", "Endereço B", "Endereço C");
        }
    }

    @Quando("eu solicito a otimização dessa rota")
    public void eu_solicito_a_otimizacao_dessa_rota() {
        respostaOtimizacao = apiService.processarMelhorRota(enderecosParaOtimizar);
    }

    @Então("o sistema deve retornar uma mensagem de sucesso confirmando o recebimento dos {int} endereços")
    public void o_sistema_deve_retornar_uma_mensagem_de_sucesso(Integer qtd) {
        assertThat(respostaOtimizacao).contains("Sucesso!");
        assertThat(respostaOtimizacao).contains(String.valueOf(qtd));
    }

    // --- CENÁRIO 2 ---
    @Dado("que eu informo os dados do usuário {string} com a senha {string} e nome {string}")
    public void que_eu_informo_os_dados_do_usuario(String username, String senha, String nome) {
        usuarioAtual = new Usuario();
        usuarioAtual.setUsername(username);
        usuarioAtual.setPassword(senha);
        usuarioAtual.setNome(nome);
    }

    @Quando("eu salvo o usuário no sistema")
    public void eu_salvo_o_usuario_no_sistema() {
        usuarioAtual = usuarioRepository.save(usuarioAtual);
    }

    @Então("o sistema deve gerar um ID válido para esse usuário")
    public void o_sistema_deve_gerar_um_id_valido() {
        assertThat(usuarioAtual.getId()).isNotNull();
    }

    // --- CENÁRIO 3 ---
    @Dado("que eu procuro o histórico de rotas de um usuário inexistente {string}")
    public void que_eu_procuro_o_historico_de_um_usuario_inexistente(String username) {
        usuarioAtual = usuarioRepository.findByUsername(username); // Será null
    }

    @Quando("eu solicito as rotas salvas")
    public void eu_solicito_as_rotas_salvas() {
        if (usuarioAtual == null) {
            historicoResultado = List.of();
        } else {
            historicoResultado = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuarioAtual);
        }
    }

    @Então("o sistema deve retornar uma lista vazia de rotas")
    public void o_sistema_deve_retornar_uma_lista_vazia() {
        assertThat(historicoResultado).isEmpty();
    }

    // --- CENÁRIO 4 ---
    @Dado("que existe um usuário {string} salvo no sistema")
    public void que_existe_um_usuario_salvo(String username) {
        usuarioAtual = new Usuario();
        usuarioAtual.setUsername(username);
        usuarioAtual.setPassword("senha_padrao");
        usuarioAtual.setNome("Nome Padrão");
        usuarioAtual = usuarioRepository.save(usuarioAtual);
    }

    @E("eu crio uma rota com origem {string} e paradas {string}")
    public void eu_crio_uma_rota_com_origem_e_paradas(String origem, String paradas) {
        rotaAtual = new Rota();
        rotaAtual.setOrigem(origem);
        rotaAtual.setParadas(paradas);
        rotaAtual.setDataCriacao(LocalDateTime.now());
    }

    @Quando("eu vinculo a rota a esse usuário e salvo")
    public void eu_vinculo_a_rota_a_esse_usuario_e_salvo() {
        rotaAtual.setUsuario(usuarioAtual);
        rotaRepository.save(rotaAtual);
    }

    @Então("o histórico do usuário {string} deve conter {int} rota salva")
    public void o_historico_do_usuario_deve_conter_rota_salva(String username, Integer qtd) {
        Usuario user = usuarioRepository.findByUsername(username);
        List<Rota> rotas = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(user);
        assertThat(rotas).hasSize(qtd);
    }

    // --- CENÁRIO 5 ---
    @E("esse usuário possui uma rota antiga de {string} e uma nova de {string}")
    public void esse_usuario_possui_rotas_com_tempos_diferentes(String rotaAntiga, String rotaNova) {
        Rota r1 = new Rota();
        r1.setOrigem(rotaAntiga);
        r1.setUsuario(usuarioAtual);
        r1.setDataCriacao(LocalDateTime.now().minusDays(1));
        rotaRepository.save(r1);

        Rota r2 = new Rota();
        r2.setOrigem(rotaNova);
        r2.setUsuario(usuarioAtual);
        r2.setDataCriacao(LocalDateTime.now());
        rotaRepository.save(r2);
    }

    @Quando("eu busco o histórico de rotas ordenado")
    public void eu_busco_o_historico_de_rotas_ordenado() {
        historicoResultado = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuarioAtual);
    }

    @Então("a primeira rota da lista deve ser a de {string}")
    public void a_primeira_rota_da_lista_deve_ser(String origemEsperada) {
        assertThat(historicoResultado.get(0).getOrigem()).isEqualTo(origemEsperada);
    }
}
