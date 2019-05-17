package readingTracker.com.br.BLL;

import readingTracker.com.br.dao.DaoLivro;
import readingTracker.com.br.model.LivroModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivroBLL {

    private DaoLivro daoLivro = new DaoLivro();

    public boolean verificaCampos(LivroModel oLivro) {

        if(oLivro.getTitulo().isEmpty()){

            return false;
        }

        return true;
    }

    public boolean novoLivro(LivroModel oLivro) {
        try {
            if (verificaCampos(oLivro)) {
                return daoLivro.Save(oLivro);
            }
        } catch (Exception ex) {
            Logger.getLogger(LivroBLL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public boolean editarLivro(LivroModel oLivro) {
        try {
            if (verificaCampos(oLivro)) {
                return daoLivro.Update(oLivro);
            }
        } catch (Exception ex) {
            Logger.getLogger(LivroBLL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public List<LivroModel> buscaLivros() {

        List<LivroModel> lstLivro = new ArrayList<>();
        List<Object> lstObj = daoLivro.get();

        for(Object obj : lstObj){
            lstLivro.add((LivroModel)obj);
        }
/*
        if (lstLivro.isEmpty()) {

            BooksBLL books = BooksBLL.getInstance();
            lstLivro = books.doGetList(Titulo);

            return lstLivro;
        }
*/
        return lstLivro;
    }

    public Object selecionaLivro(String Titulo) {

        try {
            Object obj = new DaoLivro().selectLivroByTitulo(Titulo);
            LivroModel oLivro = null;

            if (obj instanceof LivroModel) {
                oLivro = (LivroModel) obj;

            } else {
                return false;
            }

            return null;

        } catch (Exception ex) {
            Logger.getLogger(LivroBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}
