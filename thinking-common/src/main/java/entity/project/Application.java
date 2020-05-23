package entity.project;

import entity.serializable.Person;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicUpdate(true)
@Table(name = "t_table")
public class Application extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_name")
    @SequenceGenerator(name = "sequence_name", sequenceName = "sequence_name", allocationSize = 1)
    private long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name="FLAG")
    private Integer flag;

    // bi-directional many-to-one association to checkJournal
    @OneToMany(mappedBy = "application")
    private Set<Integer> increments;

    @OneToOne(mappedBy = "application")
    private Employee employee;

    @OneToMany(mappedBy = "application", fetch = FetchType.EAGER)
    private Set<Person> persons;

    public Application() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Set<Integer> getIncrements() {
        return increments;
    }

    public void setIncrements(Set<Integer> increments) {
        this.increments = increments;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}