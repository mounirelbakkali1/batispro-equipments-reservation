package ma.youcode.batispro.service;

import ma.youcode.batispro.domain.entity.Bill;

public interface IContractGenerator {
    public byte[] generateContract(Bill bill) throws Exception;
}
