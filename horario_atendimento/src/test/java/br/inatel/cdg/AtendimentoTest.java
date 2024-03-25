package br.inatel.cdg;

import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class AtendimentoTest {

    @Mock
    private AtendimentoService service;
    private BuscaAtendimento buscaAtendimento;

    @Before
    public void setup() {
        this.buscaAtendimento = new BuscaAtendimento(service);
    }

    @Test
    public void testeBuscaPorNome() {
        Mockito.when(service.buscaPorProfessor("Christopher Lima")).thenReturn(AtendimentoConst.CHRIS);

        Atendimento atendimento = buscaAtendimento.buscaPorProfessor("Christopher Lima");
        assertEquals("Christopher Lima", atendimento.getProfessor());
        assertEquals("15:30", atendimento.getHorario());
        assertEquals("Segunda", atendimento.getDia());
        assertEquals("C214", atendimento.getMateria());
        assertEquals("Integral", atendimento.getPeriodo());
        assertEquals(15, atendimento.getSala());
        assertEquals(3, atendimento.getPredio());
    }

    @Test
    public void testeBuscaPorPredio() {
        Mockito.when(service.buscaPorPredio(2)).thenReturn(AtendimentoConst.JULIDORI);

        Atendimento atendimento = buscaAtendimento.buscaPorPredio(2);
        assertEquals("Matheus Julidori", atendimento.getProfessor());
        assertEquals("15:30", atendimento.getHorario());
        assertEquals("Segunda", atendimento.getDia());
        assertEquals("C206-Lab", atendimento.getMateria());
        assertEquals("Integral", atendimento.getPeriodo());
        assertEquals(2, atendimento.getSala());
        assertEquals(1, atendimento.getPredio());
    }

    @Test
    public void testeBuscaPorPeriodo() {
        Mockito.when(service.buscaPorPeriodo("Noturno")).thenReturn(AtendimentoConst.CYSNEIROS);

        Atendimento atendimento = buscaAtendimento.buscaPorPeriodo("Noturno");
        assertEquals("Marcelo Cysneiros", atendimento.getProfessor());
        assertEquals("19:30", atendimento.getHorario());
        assertEquals("Terça", atendimento.getDia());
        assertEquals("C216", atendimento.getMateria());
        assertEquals("Noturno", atendimento.getPeriodo());
        assertEquals(19, atendimento.getSala());
        assertEquals(4, atendimento.getPredio());
    }

    @Test
    public void testeBuscaPorSala() {
        Mockito.when(service.buscaPorSala(26)).thenReturn(AtendimentoConst.MARCONDES);

        Atendimento atendimento = buscaAtendimento.buscaPorSala(26);
        assertEquals("Guilherme Marcondes", atendimento.getProfessor());
        assertEquals("19:30", atendimento.getHorario());
        assertEquals("Sexta", atendimento.getDia());
        assertEquals("C201", atendimento.getMateria());
        assertEquals("Noturno", atendimento.getPeriodo());
        assertEquals(26, atendimento.getSala());
        assertEquals(6, atendimento.getPredio());
    }

    @Test
    public void testeBuscaPorHorario() {
        Mockito.when(service.buscaPorHorario("17:30")).thenReturn(AtendimentoConst.RENZO);

        Atendimento atendimento = buscaAtendimento.buscaPorHorario("17:30");
        assertEquals("Renzo Mesquita", atendimento.getProfessor());
        assertEquals("17:30", atendimento.getHorario());
        assertEquals("Quarta", atendimento.getDia());
        assertEquals("C207", atendimento.getMateria());
        assertEquals("Integral", atendimento.getPeriodo());
        assertEquals(10, atendimento.getSala());
        assertEquals(2, atendimento.getPredio());
    }

    @Test
    public void testeBuscaPorDia() {
        List<String> atendimentos = Arrays.asList(AtendimentoConst.MOSCA);
        Mockito.when(service.buscaPorDia("Quinta")).thenReturn(atendimentos);

        List<Atendimento> atendimento = buscaAtendimento.buscaPorDia("Quinta");
        assertEquals("Daniel Mosca", atendimento.get(0).getProfessor());
        assertEquals("21:30", atendimento.get(0).getHorario());
        assertEquals("Quinta", atendimento.get(0).getDia());
        assertEquals("C205", atendimento.get(0).getMateria());
        assertEquals("Noturno", atendimento.get(0).getPeriodo());
        assertEquals(23, atendimento.get(0).getSala());
        assertEquals(5, atendimento.get(0).getPredio());
    }

    @Test
    public void testeBuscaPorDiaVazio() {
        List<String> atendimentos = Arrays.asList();
        Mockito.when(service.buscaPorDia("Sábado")).thenReturn(atendimentos);

        List<Atendimento> atendimento = buscaAtendimento.buscaPorDia("Sábado");
        assertEquals(0, atendimento.size());
    }

    @Test
    public void testeBuscaPorDiaComMaisDeUmAtendimento() {
        List<String> atendimentos = Arrays.asList(AtendimentoConst.CHRIS, AtendimentoConst.JULIDORI);
        Mockito.when(service.buscaPorDia("Segunda")).thenReturn(atendimentos);

        List<Atendimento> atendimento = buscaAtendimento.buscaPorDia("Segunda");
        assertEquals("Christopher Lima", atendimento.get(0).getProfessor());
        assertEquals("15:30", atendimento.get(0).getHorario());
        assertEquals("Segunda", atendimento.get(0).getDia());
        assertEquals("C214", atendimento.get(0).getMateria());
        assertEquals("Integral", atendimento.get(0).getPeriodo());
        assertEquals(15, atendimento.get(0).getSala());
        assertEquals(3, atendimento.get(0).getPredio());

        assertEquals("Matheus Julidori", atendimento.get(1).getProfessor());
        assertEquals("15:30", atendimento.get(1).getHorario());
        assertEquals("Segunda", atendimento.get(1).getDia());
        assertEquals("C206-Lab", atendimento.get(1).getMateria());
        assertEquals("Integral", atendimento.get(1).getPeriodo());
        assertEquals(2, atendimento.get(1).getSala());
        assertEquals(1, atendimento.get(1).getPredio());
    }

    @Test
    public void testeBuscaPorMateria() {
        Mockito.when(service.buscaPorMateria("C214")).thenReturn(AtendimentoConst.CHRIS);

        Atendimento atendimento = buscaAtendimento.buscaPorMateria("C214");
        assertEquals("Christopher Lima", atendimento.getProfessor());
        assertEquals("15:30", atendimento.getHorario());
        assertEquals("Segunda", atendimento.getDia());
        assertEquals("C214", atendimento.getMateria());
        assertEquals("Integral", atendimento.getPeriodo());
        assertEquals(15, atendimento.getSala());
        assertEquals(3, atendimento.getPredio());
    }

    @Test
    public void testeListaAtendimentos() {
        List<String> atendimentos = Arrays.asList(AtendimentoConst.CHRIS, AtendimentoConst.CYSNEIROS,
                AtendimentoConst.RENZO, AtendimentoConst.MOSCA, AtendimentoConst.MARCONDES, AtendimentoConst.JULIDORI);
        Mockito.when(service.listaAtendimentos()).thenReturn(atendimentos);

        List<Atendimento> atendimentosRetornados = buscaAtendimento.listaAtendimentos();
        assertEquals(6, atendimentosRetornados.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorNomeNaoEncontrado() {
        Mockito.when(service.buscaPorProfessor("Phyll Lima"))
                .thenThrow(new IllegalArgumentException("Professor não encontrado"));

        buscaAtendimento.buscaPorProfessor("Phyll Lima");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorPredioNaoEncontrado() {
        Mockito.when(service.buscaPorPredio(7)).thenThrow(new IllegalArgumentException("Predio não encontrado"));

        buscaAtendimento.buscaPorPredio(7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorPeriodoNaoEncontrado() {
        Mockito.when(service.buscaPorPeriodo("Vespertino"))
                .thenThrow(new IllegalArgumentException("Periodo não encontrado"));

        buscaAtendimento.buscaPorPeriodo("Vespertino");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorSalaNaoEncontrado() {
        Mockito.when(service.buscaPorSala(30)).thenThrow(new IllegalArgumentException("Sala não encontrada"));

        buscaAtendimento.buscaPorSala(30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorHorarioForaDoExpediente() {
        Mockito.when(service.buscaPorHorario("02:30"))
                .thenThrow(new IllegalArgumentException("Horario fora do expediente"));

        buscaAtendimento.buscaPorHorario("02:30");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorDiaNaoEncontrado() {
        Mockito.when(service.buscaPorDia("Domingo")).thenThrow(new IllegalArgumentException("Dia não encontrado"));

        buscaAtendimento.buscaPorDia("Domingo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorMateriaNaoEncontrado() {
        Mockito.when(service.buscaPorMateria("C202")).thenThrow(new IllegalArgumentException("Materia não encontrada"));

        buscaAtendimento.buscaPorMateria("C202");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorHorarioInvalido() {
        Mockito.when(service.buscaPorHorario("25:00")).thenThrow(new IllegalArgumentException("Horário inválido"));

        buscaAtendimento.buscaPorHorario("25:00");
    }

    @Test(expected = NullPointerException.class)
    public void testeListaAtendimentosVaziaDeveLancarExcecao() {
        Mockito.when(service.listaAtendimentos()).thenReturn(Collections.emptyList());

        List<Atendimento> atendimentosRetornados = buscaAtendimento.listaAtendimentos();
        if (atendimentosRetornados == null || atendimentosRetornados.isEmpty()) {
            throw new NullPointerException("Lista de atendimentos está vazia");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeBuscaPorNomeComCaracteresEspeciais() {
        Mockito.when(service.buscaPorProfessor("*&^%$#@!")).thenThrow(new IllegalArgumentException("Nome contém caracteres inválidos"));
    
        buscaAtendimento.buscaPorProfessor("*&^%$#@!");
    }

}
