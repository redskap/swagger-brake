package io.redskap.swagger.brake.core.model.schemastore;

public class SchemaStoreProvider {
    private static final ThreadLocal<SchemaStore> schemaStore = new ThreadLocal<>();

    public static void setSchemaStore(SchemaStore store) {
        schemaStore.set(store);
    }

    public static SchemaStore provide() {
        return schemaStore.get();
    }

    public static void clear() {
        schemaStore.remove();
    }
}
