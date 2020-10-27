package net.jagunma;

import java.util.List;
import net.jagunma.backbone.shared.application.branch.mock.BranchMock;
import net.jagunma.backbone.shared.application.ja.JaMock;
import net.jagunma.common.values.model.branch.Branch;
import net.jagunma.common.values.model.ja.Ja;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackboneAuthOa1ApplicationLocal {

    @Bean("branches")
    public List<Branch> getBranches() {
        BranchMock.init();
        return BranchMock.branches;
    }

    @Bean("jas")
    public List<Ja> getJas() {
        return JaMock.全JA;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackboneAuthOa1ApplicationLocal.class, args);

    }

}
