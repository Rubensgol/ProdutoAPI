package main.com.Control;

import java.util.List;
import main.com.model.Produto;

public interface iCrud {
    List<Produto> findAll();

    boolean insert(Produto produto);

    boolean updateUser(Produto produto,int codigo);

    boolean deleteUser(int codigo);
	
    public Produto findOne(int codigo);
}
