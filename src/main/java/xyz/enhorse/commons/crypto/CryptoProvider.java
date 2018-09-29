package xyz.enhorse.commons.crypto;

import java.security.Provider;
import java.security.Security;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CryptoProvider {

    private final Provider provider;
    private final List<String> content;


    public CryptoProvider(final Provider provider) {
        this.provider = Objects.requireNonNull(provider);
        content = Collections.list(provider.keys()).stream()
                .map(key -> ((String) key).toUpperCase())
                .filter(key -> !key.contains(" "))
                .collect(Collectors.toList());
    }


    public Provider provider() {
        return provider;
    }


    public String name() {
        return provider().getName();
    }


    public double version() {
        return provider().getVersion();
    }


    public Set<String> keystores() {
        return filter("Keystore");
    }


    public Set<String> signatures() {
        return filter("Signature");
    }


    public Set<String> ciphers() {
        return filter("Cipher");
    }


    public Set<String> digests() {
        return filter("MessageDigest");
    }


    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(name(), version());
    }


    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final CryptoProvider that = (CryptoProvider) o;
        return Double.compare(version(), that.version()) == 0
                && Objects.equals(name(), that.name());
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return name() + " v." + version();
    }


    private Set<String> filter(final String value) {
        final String entity = value.toUpperCase();
        final int length = value.length();

        return content.stream()
                .filter(key -> key.startsWith(entity))
                .map(key -> key.substring(length + 1))
                .collect(Collectors.toSet());
    }


    public static Optional<CryptoProvider> forName(final String name) {
        return Stream.of(Security.getProviders())
                .filter(provider -> provider.getName().equalsIgnoreCase(name))
                .map(CryptoProvider::new)
                .findFirst();
    }
}
