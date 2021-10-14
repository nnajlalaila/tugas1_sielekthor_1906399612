package apap.tugas.sielekthor.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@Table(name = "barang")
public class BarangModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String namaBarang;

    @NotNull
    @Column(nullable = false)
    private Integer stok;

    @NotNull
    @Column(nullable = false)
    private Integer jumlahGaransi;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String deskripsiBarang;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String kodeBarang;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String merkBarang;

    @NotNull
    @Column(nullable = false)
    private Integer hargaBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PembelianBarangModel> listPembelianBarang;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IdTipe", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TipeModel tipe;

//
//    @ManyToMany(mappedBy = "listBarang")
//    private List<PembelianModel> listPembelian;

}