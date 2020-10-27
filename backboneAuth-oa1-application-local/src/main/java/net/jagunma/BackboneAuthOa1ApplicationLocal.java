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
        BranchMock.init();
        return BranchMock.branches;
    }

    @Bean("jas")
    public List<Ja> getJas() {
        return JaMock.全JA;
    }

    @Bean("banks")
    public List<Bank> getBanks() {
        BankMock.init();
        return BankMock.getData();
    }

    @Bean("bankBranches")
    public List<BankBranch> getBankBranches() {
        BankBranchMock.init();
        return BankBranchMock.getData();
    }

    @Bean("simpleAddresses")
    public List<SimpleAddress> getSimpleAddresses() {
        SimpleAddressMock.init();
        return SimpleAddressMock.getData();
    }

    @Bean("prefectures")
    public List<Prefecture> getPrefectures() {
        return PrefecturesMock.全都道府県;
    }

    @Bean("cities")
    public List<City> getCities() {
        var mock = new CitiesMock();
        return mock.群馬県;
    }

    @Bean("azas")
    public List<Aza> getAzas() {
        AzasMock.init();
        return AzasMock.getData();
    }

    @Bean("ooAzas")
    public List<OoAza> getOoAza() {
        OoAzaMock.init();
        return OoAzaMock.getData();
    }

    public static void main(String[] args) {
        SpringApplication.run(BackboneAuthOa1ApplicationLocal.class, args);

    }

}
