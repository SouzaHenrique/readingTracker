package readingTracker.com.br.BLL;

import readingTracker.com.br.dao.DaoLivro;
import readingTracker.com.br.model.LivroModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivroBLL {

    private DaoLivro daoLivro = new DaoLivro();

    public boolean verificaCampos(String Titulo) {

        if (Titulo.equals(null)) {

            return false;
        }

        return true;
    }

    public boolean novoLivro(LivroModel oLivro){
        try {
            if (daoLivro.Save(oLivro)){
                return true;
            }
        }catch (Exception ex){
            Logger.getLogger(LeituraBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editarLivro(LivroModel oLivro){
        try {
            if (daoLivro.Update(oLivro)) {
                return true;
            }
        }catch (Exception ex){
            Logger.getLogger(LeituraBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<LivroModel> buscaLivros(String Titulo) {

        List<LivroModel> lstLivro = new ArrayList<>();
        lstLivro = daoLivro.getListByTitulo(Titulo);

        if (lstLivro.isEmpty()){

            BooksBLL books = BooksBLL.getInstance();
            lstLivro = books.doGetList(Titulo);

            return lstLivro;
        }

            return lstLivro;
    }
}
