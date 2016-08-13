/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dggodoi.aprendendo.java.backend.business;

import io.github.dggodoi.aprendendo.java.backend.data.Usuario;
import io.github.dggodoi.aprendendo.java.backend.enumerado.usuario.Tipo;
import io.github.dggodoi.aprendendo.java.backend.infra.HibernateUtil;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author aluno
 */
public class UsuarioBus {
    // TODO Remover método temporario

    public static Usuario selecionarAluno() {
        Usuario aluno = (Usuario) HibernateUtil.getSessionFactory()
                .openSession()
                .createQuery("from Usuario where login = :login")
                .setString("login", "aluno")
                .uniqueResult();

        if (aluno == null) {
            aluno = new Usuario();
            aluno.setLogin("aluno");
            aluno.setSenha(DigestUtils.sha256Hex("123"));
            aluno.setNome("Aluno Teste");
            aluno.setTipo(Tipo.SUPORTE);
            aluno.setDataRegistro(new Date());
            aluno.setAtivo(true);

            UsuarioBus usuarioBus = new UsuarioBus();
            usuarioBus.inserir(aluno);
        }

        return aluno;
    }

    public Long inserir(Usuario usuario) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(usuario);
        t.commit();
        return usuario.getId();
    }

    public Usuario selecionar(long id) {
        return (Usuario) HibernateUtil.getSessionFactory()
                .openSession()
                .get(Usuario.class, id);
    }
}
