package fr.free.bkake.springjooq.utils;


import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * Created by bangaly.kake on 29/06/2017.
 */
@Value.Immutable
public interface References {
    @Value.Parameter
    String deviseFactureTravaux();
    @Value.Parameter
    String modePaiement();
    @Value.Parameter
    @Nullable
    String numeroMarche();
    @Value.Parameter
    @Nullable
    String numeroBonCommande();
    @Value.Default
    default String typeTva() {
        return "TVA_SUR_DEBIT";
    }
}
