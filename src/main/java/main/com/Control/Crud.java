package main.com.Control;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import main.com.model.Produto;

@Repository
public class Crud implements iCrud {
	NamedParameterJdbcTemplate template;

	public Crud(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	public List<Produto> findAll() {
		return template.query("select * from PRODUTO", new ProdutoMapper());
	}

	public boolean insert(Produto produto) {
		final String sql = "insert into PRODUTO(codigo,nome,preco,marca) values(:codigo,:nome,:preco,:marca)";
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource()//
				.addValue("codigo", produto.getCodigo()) //
				.addValue("nome", produto.getNome()).//
				addValue("preco", produto.getPreco())//
				.addValue("marca", produto.getMarca());
		if (template.update(sql, param, holder) != 0)
			return true;
		return false;

	}

	public boolean updateUser(Produto produto, int codigo) {
		final String sql = "update PRODUTO set codigo = :codigo, nome = :nome,preco = :preco,marca = :marca where codigo=:codigoP";
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource()//
				.addValue("codigo", produto.getCodigo()) //
				.addValue("nome", produto.getNome()).//
				addValue("preco", produto.getPreco())//
				.addValue("marca", produto.getMarca())//
				.addValue("codigoP", codigo);
		try {
			if (template.update(sql, param, holder) != 0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	public Produto findOne(int codigo) {
		
		final String sql = "select * from PRODUTO where codigo=:codigo";
		SqlParameterSource param = new MapSqlParameterSource().addValue("codigo", codigo);
		try {
			return template.queryForObject(sql, param, new ProdutoMapper());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public boolean deleteUser(int codigo) {
		// DELETE FROM table_name WHERE condition;
		final String sql = "delete from PRODUTO where codigo=:codigo";
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource()//
				.addValue("codigo", codigo);

		if (template.update(sql, param, holder) != 0)
			return true;
		return false;

	}

}
