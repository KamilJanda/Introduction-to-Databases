package generator;

import org.hibernate.SessionFactory;

public abstract class Generator {

    protected SessionFactory sessionFactory;

    public Generator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
