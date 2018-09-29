package xyz.enhorse.commons.crypto;

import java.security.Provider;
import java.security.Security;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CryptoSystem {
    INSTANCE;

    private final Set<Provider> providers;


    CryptoSystem() {
        providers = Stream.of(Security.getProviders()).collect(Collectors.toSet());
    }


    public Set<Provider> providers() {
        return providers;
    }


    public Set<String> keystores() {
        return adopt(CryptoProvider::keystores);
    }


    public Set<String> signatures() {
        return adopt(CryptoProvider::signatures);
    }


    public Set<String> ciphers() {
        return adopt(CryptoProvider::ciphers);
    }


    public Set<String> digests() {
        return adopt(CryptoProvider::digests);
    }


    private Set<String> adopt(final Function<CryptoProvider, Set<String>> supplier) {
        return providers().stream()
                .map(CryptoProvider::new)
                .map(supplier)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
