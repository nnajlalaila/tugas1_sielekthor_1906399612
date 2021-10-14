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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "pembelian")
public class PembelianModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer total;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(nullable = false)
    private Date tanggalPembelian;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String namaAdmin;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String noInvoice;

    @NotNull
    @Column(nullable = false)
    private Boolean isCash;

//    @ManyToMany
//    @JoinTable(
//            name = "pembelianbarang",
//            joinColumns = @JoinColumn(name = "idPembelian"),
//            inverseJoinColumns = @JoinColumn(name = "idBarang")
//    )
//    List<BarangModel> listBarang;


    @OneToMany(mappedBy = "pembelian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PembelianBarangModel> listPembelianBarang;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IdMember", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MemberModel member;

}