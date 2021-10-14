package apap.tugas.sielekthor.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "tipe")
//Entitas TIPE merepresentasikan tipe dari barang. TIPE memiliki id, namaTipe, dan
//deskripsiTipe. Id merupakan identifier yang auto increment.
public class TipeModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String deskripsiTipe;

    @OneToMany(mappedBy = "tipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BarangModel> listBarang;

}
