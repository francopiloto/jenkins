package ifce.polo.sippi.model.config;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
public class TableItemList 
{
	private Short id;
	private String nome;
	private boolean ativado;
	private BigDecimal vinculos;

/* --------------------------------------------------------------------------------------------- */

	public TableItemList(Short id, String nome, BigDecimal vinculos, boolean ativado) 
	{
		this.ativado = ativado;
		this.id = id;
		this.nome = nome;
		this.vinculos = vinculos;
	}

/* --------------------------------------------------------------------------------------------- */

}
