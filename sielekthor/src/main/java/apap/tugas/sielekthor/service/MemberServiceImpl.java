package apap.tugas.sielekthor.service;
import javax.transaction.Transactional;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.repository.MemberDB;
import apap.tugas.sielekthor.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
