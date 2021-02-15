package ifce.polo.sippi.service.register;

public class Availability
{
    public static enum Status
    {
        INVALID("invalid"),
        REGISTERED("registered"),
        PENDING("pending"),
        AVAILABLE("available");

        public final String value;

        private Status(String value) {
            this.value = value;
        }
    }

    public static final Availability INVALID = new Availability(Status.INVALID);
    public static final Availability REGISTERED = new Availability(Status.REGISTERED);
    public static final Availability PENDING = new Availability(Status.PENDING);
    public static final Availability AVAILABLE = new Availability(Status.AVAILABLE);

    private final Status status;
    private final Long id;
    private final String name;

/* --------------------------------------------------------------------------------------------- */

    public static final Availability available(Long reference, String name) {
        return new Availability(Status.AVAILABLE, reference, name);
    }

/* --------------------------------------------------------------------------------------------- */

    private Availability(Status status) {
        this(status, null, null);
    }

/* --------------------------------------------------------------------------------------------- */

    private Availability(Status status, Long id, String name)
    {
        this.status = status;
        this.id = id;
        this.name = name;
    }

/* --------------------------------------------------------------------------------------------- */

    public Status getStatus() {
        return status;
    }

/* --------------------------------------------------------------------------------------------- */

    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    public String getName() {
        return name;
    }

/* --------------------------------------------------------------------------------------------- */

}
