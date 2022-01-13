package com.eventoweb.controllers;

import com.eventoweb.models.Convidado;
import com.eventoweb.repository.ConvidadoRepository;
import com.eventoweb.repository.EventoRepository;
import com.eventoweb.models.Evento;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired// fornece controle sobre onde e como a ligação entre os beans deve ser realizada.
    private EventoRepository er;//variavel

    @Autowired
    private ConvidadoRepository cr;

// repository é um objeto que isola os objetos ou entidades do domínio do código que acessa o banco de dados.
/*
Um repositório é essencialmente uma coleção de objetos de domínio em memória, e,
 com base nisso o padrão Repository permite realizar o isolamento entre a camada
  de acesso a dados (DAL) de sua aplicaçãoe sua camada de apresentação (UI) e camada de negócios (BLL).
 */

    @RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
    public String form(){//criando metodo

        return "formEvento";
    }
    //Os métodos determinam o comportamento dos objetos de uma classe
    @RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarEvento";
        }

        er.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/eventos")
    public ModelAndView listaEventos (){// metodo prara retornar a lista

        ModelAndView mv= new ModelAndView("index");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos",eventos);
        return mv;
    }

    @RequestMapping(value="/{codigo}", method=RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("detalhesEvento");
        mv.addObject("evento", evento);

        Iterable<Convidado> convidado = cr.findByEvento(evento);
        mv.addObject("convidado", convidado);

        return mv;

    }

    @RequestMapping ("/deletarEvento")
    public String deletarEvento(long codigo){
        Evento evento = er.findByCodigo(codigo);
        er.delete(evento);

        return "redirect:/eventos";


    }
    @RequestMapping(value="/{codigo}", method=RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){
if(result.hasErrors()){
    attributes.addFlashAttribute("mensagem", "Verifique os campos!!!");
    return "redirect:/{codigo}";

}
        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado adcicionado com sucesso!!!");
        return "redirect:/{codigo}";

    }

    @RequestMapping ("/deletarConvidado")
    public String deletarConvidado(String rg){
        Convidado convidado= cr.findByRg(rg);
        cr.delete(convidado);

        Evento evento = convidado.getEvento();
        long codigolong = evento.getCodigo();
        String codigo = ""+codigolong;
        return "redirect:/"+ codigo;

    }
}
