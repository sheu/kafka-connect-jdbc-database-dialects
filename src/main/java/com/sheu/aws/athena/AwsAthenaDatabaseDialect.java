package com.sheu.aws.athena;


import io.confluent.connect.jdbc.dialect.DatabaseDialect;
import io.confluent.connect.jdbc.dialect.DatabaseDialectProvider;
import io.confluent.connect.jdbc.dialect.DatabaseDialectProvider.FixedScoreProvider;
import io.confluent.connect.jdbc.dialect.GenericDatabaseDialect;
import io.confluent.connect.jdbc.source.ColumnMapping;
import io.confluent.connect.jdbc.util.IdentifierRules;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.connect.data.Struct;



/**
 * A {@link DatabaseDialect} implementation that provides functionality based upon JDBC and SQL.
 *
 * <p>This class is designed to be extended as required to customize the behavior for a specific
 * DBMS. For example, override the {@link #createColumnConverter(ColumnMapping)} method to customize
 * how a column value is converted to a field value for use in a {@link Struct}. To also change the
 * field's type or schema, also override the {@link #addFieldToSchema} method.
 */
public class AwsAthenaDatabaseDialect extends GenericDatabaseDialect {


    /**
     * The provider for {@link AwsAthenaDatabaseDialect}.
     */
    public static class Provider extends FixedScoreProvider {
        public Provider() {
            super(AwsAthenaDatabaseDialect.class.getSimpleName(),
                    DatabaseDialectProvider.AVERAGE_MATCHING_SCORE
            );
        }

        @Override
        public DatabaseDialect create(AbstractConfig config) {
            return new AwsAthenaDatabaseDialect(config);
        }
    }


    /**
     * Whether to map {@code NUMERIC} JDBC types by precision.
     */


    /**
     * Create a new dialect instance with the given connector configuration.
     *
     * @param config the connector configuration; may not be null
     */
    public AwsAthenaDatabaseDialect(AbstractConfig config) {
        this(config, IdentifierRules.DEFAULT);
    }

    /**
     * Create a new dialect instance with the given connector configuration.
     *
     * @param config                 the connector configuration; may not be null
     * @param defaultIdentifierRules the default rules for identifiers; may be null if the rules are
     *                               to be determined from the database metadata
     */
    protected AwsAthenaDatabaseDialect(
            AbstractConfig config,
            IdentifierRules defaultIdentifierRules
    ) {
        super(config, defaultIdentifierRules);
    }


    /**
     * Get the query string to determine the current timestamp in the database.
     *
     * @return the query string; never null or empty
     */
    @Override
    protected String currentTimestampDatabaseQuery() {
        return "SELECT DATE_FORMAT(CURRENT_TIMESTAMP, '%Y-%m-%d %H:%i:%s')";
    }

}



