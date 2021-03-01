package net.jagunma;

import java.util.List;
import net.jagunma.backbone.shared.application.address.AzasMock;
import net.jagunma.backbone.shared.application.address.CitiesMock;
import net.jagunma.backbone.shared.application.address.OoAzaMock;
import net.jagunma.backbone.shared.application.address.PrefecturesMock;
import net.jagunma.backbone.shared.application.address.SimpleAddressMock;
import net.jagunma.backbone.shared.application.bank.BankMock;
import net.jagunma.backbone.shared.application.bank.branch.BankBranchMock;
import net.jagunma.backbone.shared.application.branch.mock.BranchMock;
import net.jagunma.backbone.shared.application.ja.JaMock;
import net.jagunma.common.ddd.model.values.buisiness.address.Aza;
import net.jagunma.common.ddd.model.values.buisiness.address.City;
import net.jagunma.common.ddd.model.values.buisiness.address.OoAza;
import net.jagunma.common.ddd.model.values.buisiness.address.Prefecture;
import net.jagunma.common.ddd.model.values.buisiness.address.SimpleAddress;
import net.jagunma.common.values.model.bank.Bank;
import net.jagunma.common.values.model.bank.branch.BankBranch;
import net.jagunma.common.values.model.branch.Branch;
import net.jagunma.common.values.model.ja.Ja;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackboneAuthOa1ApplicationLocal {

    @Bean("branches")
    public List<Branch> getBranches() {
        return BranchMock.getMockData();
    }
    @Bean("jas")
    public List<Ja> getJas() {
        return JaMock.getMockData();
    }
    @Bean("banks")
    public List<Bank> getBanks() {
        return BankMock.getMockData();
    }
    @Bean("bankBranches")
    public List<BankBranch> getBankBranches() {
        return BankBranchMock.getMockData();
    }
    @Bean("simpleAddresses")
    public List<SimpleAddress> getSimpleAddresses() {
        return SimpleAddressMock.getMockData();
    }
    @Bean("prefectures")
    public List<Prefecture> getPrefectures() {
        return PrefecturesMock.getMockData();
    }
    @Bean("cities")
    public List<City> getCities() {
        return CitiesMock.getMockData();
    }
    @Bean("azas")
    public List<Aza> getAzas() {
        return AzasMock.getMockData();
    }
    @Bean("ooAzas")
    public List<OoAza> getOoAza() {
        return OoAzaMock.getMockData();
    }

    public static void main(String[] args) {
        SpringApplication.run(BackboneAuthOa1ApplicationLocal.class, args);
    }

}
