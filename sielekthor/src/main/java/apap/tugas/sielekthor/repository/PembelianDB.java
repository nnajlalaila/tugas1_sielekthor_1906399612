package apap.tugas.sielekthor.repository;
import  apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PembelianDB extends JpaRepository<PembelianModel,Long> {
    List<PembelianModel> findAll();
    PembelianModel getPembelianModelById(Long idPembelian);
    PembelianModel findAllByMemberId(Long idMember);
    PembelianModel findAllByIsCash(boolean iscash);
}