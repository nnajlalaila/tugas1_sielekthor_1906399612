package apap.tugas.sielekthor.controller;
import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.service.*;
import apap.tugas.sielekthor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
public class MemberController {
    @Qualifier("memberServiceImpl")
    @Autowired
    private MemberService memberService;
    @Autowired
    private PembelianService pembelianService;

    @GetMapping("/member")
    public String viewAllmember(Model model){
        List<MemberModel> member = memberService.getListMember();
        model.addAttribute("listMember", member);
        return "viewall-member";
    }

    @GetMapping("/member/tambah")
    public String addMemberForm(Model model){
        model.addAttribute("member", new MemberModel());
        return "form-add-member";
    }

    @PostMapping("/member/tambah")
    public String addMemberSubmit(
            @ModelAttribute MemberModel member,
            Model model
    ){
        memberService.addMember(member);
        model.addAttribute("namaMember", member.getNamaMember());
        return "add-member";
    }

    @GetMapping("/member/ubah/{idMember}")
    public String updateMemberForm(
            @PathVariable Long idMember,
            Model model
    ){

        MemberModel member = memberService.getMemberById(idMember);
        if (member != null){
            model.addAttribute( "member", member);
            return"form-update-member";
        } else {
            model.addAttribute( "idMember", member.getId());
            return "member-not-found";
        }

    }

    @PostMapping("/member/ubah")
    public String updateMemberSubmit(
            @ModelAttribute MemberModel member,
            Model model
    ){
        memberService.updateMember(member);
        for (PembelianModel pembelian : member.getListPembelian()) {
            String newInvoice = pembelianService.generateNoInvoice(pembelian);
            pembelian.setNoInvoice(newInvoice);
        }
        memberService.updateMember(member);

        model.addAttribute("namaMember", member.getNamaMember());
        model.addAttribute("listPembelian", member.getListPembelian());
        return "update-member";
    }

    @GetMapping( "bonus/cari/member/paling-banyak")
    public String cariBestMember(Model model) {
        List<MemberModel> listTopMember = memberService.getBestMember(memberService.getListMember());
        model.addAttribute("listTopMember", listTopMember);
        return "coba-bonus";
    }


}
