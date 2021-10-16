package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;

import java.util.List;

public interface MemberService {
    List<MemberModel> getListMember();
    List<MemberModel> getBestMember(List<MemberModel> allMember);
    void addMember(MemberModel member);
    void updateMember(MemberModel member);
    MemberModel getMemberById(Long id);
}
