package apap.tugas.sielekthor.service;
import javax.transaction.Transactional;

import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.repository.MemberDB;
import apap.tugas.sielekthor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDB memberDB;

    @Override
    public void addMember(MemberModel member){
        memberDB.save(member);
    }
    @Override
    public  void updateMember(MemberModel member){
        memberDB.save(member);
    }

    @Override
    public List<MemberModel> getListMember(){
        return memberDB.findAll();
    }

    @Override
    public MemberModel getMemberById(Long id){
        return memberDB.getMemberModelById(id);
    }

//    @Override
//    public  void regenerate (MemberModel member){
//        for (PembelianModel pembelian:member.getListPembelian()) {
//            String newInvoice = pembelian.generate
//
//        };
//    }

    @Override
    public  List<MemberModel> getBestMember(List<MemberModel> allMember){
        List<MemberModel> hasil = new ArrayList<>();
        for (int i = allMember.size()-1 ; i > 0 ; i--) {
            int max = i;
            for (int j = 0; j < i ; j++) {
                if(allMember.get(j).getListPembelian().size() > allMember.get(max).getListPembelian().size()){
                    max = j;
                }
            }
            MemberModel highest = allMember.get(max);
            allMember.set(max,allMember.get(i));
            allMember.set(i,highest);
        }

            for (int i = allMember.size()-1; i >0 ; i--) {
                hasil.add(allMember.get(i));
            }
            return hasil;

    }
}
