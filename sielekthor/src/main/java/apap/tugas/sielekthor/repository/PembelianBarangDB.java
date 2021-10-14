package apap.tugas.sielekthor.repository;
import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import  apap.tugas.sielekthor.model.PembelianBarangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PembelianBarangDB extends JpaRepository<PembelianBarangModel,Long> {
    PembelianBarangModel getPembelianBarangById(Long id);
    List<PembelianBarangModel> findAll();
    List<PembelianBarangModel> findAllByPembelianAndBarang(PembelianModel pembelian, BarangModel teknisi);
}

