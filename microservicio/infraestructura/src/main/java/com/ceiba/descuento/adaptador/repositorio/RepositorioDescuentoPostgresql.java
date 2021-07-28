package com.ceiba.descuento.adaptador.repositorio;


import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioDescuentoPostgresql implements RepositorioDescuento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "descuento", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "descuento", value = "existePorRestauranteYCodigo")
    private static String sqlExistePorRestauranteYCodigo;

    @SqlStatement(namespace = "descuento", value = "descuentosPorRestaurante")
    private static String sqlDescuentosPorRestaurante;




    public RepositorioDescuentoPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Descuento descuento) {
        return this.customNamedParameterJdbcTemplate.crear(descuento,sqlCrear);
    }

    @Override
    public boolean existePorRestauranteYCodigo(Long idRestaurante, Long codigo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idRestaurante", idRestaurante);
        parameterSource.addValue("codigo", codigo);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorRestauranteYCodigo, parameterSource, Boolean.class);
    }

    @Override
    public List<Descuento> descuentosPorRestaurante(Long idRestaurante) {
        List<Descuento> descuentos = new ArrayList<>();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idRestaurante", idRestaurante);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlDescuentosPorRestaurante, parameterSource, row ->{
            Long id = row.getLong("id");
            Long codigo = row.getLong("codigo");
            BigDecimal valorDescuento = row.getBigDecimal("valor_descuento");


            Descuento descuento = new Descuento(
                    id,
                    codigo,
                    idRestaurante,
                    valorDescuento
            );

            descuentos.add(descuento);
        });
        return descuentos;
    }

}

