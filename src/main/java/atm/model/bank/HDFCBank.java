package atm.model.bank;

import atm.constant.Constants;

public class HDFCBank implements Bank {
    @Override
    public String getName() {
        return Constants.HDFC_Name;
    }

    @Override
    public String getIFSC() {
        return Constants.HDFC_IFSC;
    }
}
