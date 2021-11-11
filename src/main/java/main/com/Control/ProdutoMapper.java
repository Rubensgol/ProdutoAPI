package main.com.Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import main.com.model.Produto;

public class ProdutoMapper implements RowMapper<Produto>{

	public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
		Produto produto = new Produto();
		produto.setCodigo(rs.getInt("codigo"));
		produto.setMarca(rs.getString("marca"));
		produto.setPreco(rs.getDouble("preco"));
		produto.setNome(rs.getString("nome"));
		return produto;
	}

}
