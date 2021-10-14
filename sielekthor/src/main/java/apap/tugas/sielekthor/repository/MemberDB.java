package apap.tugas.sielekthor.repository;
import apap.tugas.sielekthor.model.BarangModel;
import  apap.tugas.sielekthor.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDB extends JpaRepository<MemberModel,Long> {
    List<MemberModel> findAll();
    MemberModel getMemberModelById(Long id);
}
