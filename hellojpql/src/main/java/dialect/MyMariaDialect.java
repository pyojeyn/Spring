package dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.MariaDB10Dialect;
import org.hibernate.dialect.MariaDBDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyMariaDialect extends H2Dialect {

    public MyMariaDialect(){
//        registerFunction("regexp_substr", new StandardSQLFunction(("regexp_substr"), StandardBasicTypes.STRING));
    }
}
