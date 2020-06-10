package org.ve;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bonge.launch.BongeLaunch;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.service.ProviderRegistration;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.service.economy.transaction.ResultType;
import org.spongepowered.api.service.economy.transaction.TransactionResult;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BongeEco implements Economy {

    private Currency currency;

    public ProviderRegistration<EconomyService> getSpongeEco() throws IOException {
        Optional<ProviderRegistration<EconomyService>> opService = Sponge.getServiceManager().getRegistration(EconomyService.class);
        if(!opService.isPresent()){
            throw new IOException("No economy service found for Sponge");
        }
        if(this.currency == null){
            this.currency = opService.get().getProvider().getDefaultCurrency();
        }
        return opService.get();
    }

    public EconomyResponse convert(TransactionResult result){
        EconomyResponse.ResponseType type = EconomyResponse.ResponseType.FAILURE;
        String errorMessage = result.getType().getName();
        if(result.getResult().equals(ResultType.SUCCESS)){
            type = EconomyResponse.ResponseType.SUCCESS;
            errorMessage = null;
        }
        return new EconomyResponse(result.getAmount().doubleValue(), result.getAccount().getBalance(result.getCurrency()).doubleValue(), type, errorMessage);
    }

    public UniqueAccount getSpongeAccount(OfflinePlayer player) throws IOException{
        ProviderRegistration<EconomyService> service = this.getSpongeEco();
        Optional<UniqueAccount> opAccount = service.getProvider().getOrCreateAccount(player.getUniqueId());
        if(opAccount.isPresent()){
            return opAccount.get();
        }
        throw new IOException("Can not create new Sponge account for economy");
    }

    @Override
    public boolean isEnabled() {
        try {
            getSpongeEco();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String getName() {
        return "BongeEco";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return null;
    }

    @Override
    public String currencyNameSingular() {
        return null;
    }

    @Override
    public boolean hasAccount(String s) {
        return this.hasAccount(Bukkit.getOfflinePlayer(s));
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        try {
            return this.getSpongeEco().getProvider().hasAccount(offlinePlayer.getUniqueId());
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return this.hasAccount(Bukkit.getOfflinePlayer(s), s1);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return this.hasAccount(offlinePlayer);
    }

    @Override
    public double getBalance(String s) {
        return this.getBalance(Bukkit.getOfflinePlayer(s));
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        try {
            return this.getSpongeAccount(offlinePlayer).getBalance(this.currency).doubleValue();
        } catch (IOException e) {
            return 0.0;
        }
    }

    @Override
    public double getBalance(String s, String s1) {
        return this.getBalance(Bukkit.getOfflinePlayer(s), s1);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return this.getBalance(offlinePlayer);
    }

    @Override
    public boolean has(String s, double v) {
        return this.has(Bukkit.getOfflinePlayer(s), v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return this.getBalance(offlinePlayer) >= v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return this.has(Bukkit.getOfflinePlayer(s), s1, v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return this.has(offlinePlayer, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return this.withdrawPlayer(s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        Cause cause = Cause.builder().append(offlinePlayer).build(EventContext.builder().add(EventContextKeys.PLUGIN, BongeLaunch.getContainer()).build());
        try {
            return this.convert(this.getSpongeAccount(offlinePlayer).withdraw(this.currency, new BigDecimal(v), cause));
        } catch (IOException e) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Unknown Sponge economy service");
        }
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return this.withdrawPlayer(s, s1, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return this.withdrawPlayer(offlinePlayer, v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return depositPlayer(Bukkit.getOfflinePlayer(s), v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        Cause cause = Cause.builder().append(offlinePlayer).build(EventContext.builder().add(EventContextKeys.PLUGIN, BongeLaunch.getContainer()).build());
        try {
            return this.convert(this.getSpongeAccount(offlinePlayer).deposit(this.currency, new BigDecimal(v), cause));
        } catch (IOException e) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Unknown Sponge economy service");
        }
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return this.depositPlayer(s, v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return this.depositPlayer(offlinePlayer, v);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "No bank support in Sponge");
    }

    @Override
    public List<String> getBanks() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return this.createPlayerAccount(Bukkit.getOfflinePlayer(s));
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        try {
            this.getSpongeAccount(offlinePlayer);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return this.createPlayerAccount(Bukkit.getOfflinePlayer(s), s1);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return this.createPlayerAccount(offlinePlayer);
    }
}
