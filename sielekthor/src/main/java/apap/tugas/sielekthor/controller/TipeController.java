package apap.tugas.sielekthor.controller;
import apap.tugas.sielekthor.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;

public class TipeController {
    @Qualifier("tipeServiceImpl")
    @Autowired
    TipeService tipeService;

}
