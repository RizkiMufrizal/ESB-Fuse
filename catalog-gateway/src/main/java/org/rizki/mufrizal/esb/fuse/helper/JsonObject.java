package org.rizki.mufrizal.esb.fuse.helper;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 23 February 2019
 * @Time 22:19
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.helper
 * @File JsonObject
 */

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonObject extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public JsonObject() {
    }

    public JsonObject(Map<String, ?> map) {
        super(map);
    }

    public BigDecimal getBigDecimal(String key) {
        Object returnable = this.get(key);
        if (!(returnable instanceof BigDecimal)) {
            if (returnable instanceof Number) {
                returnable = new BigDecimal(returnable.toString());
            } else if (returnable instanceof String) {
                returnable = new BigDecimal((String) returnable);
            }
        }

        return (BigDecimal) returnable;
    }

    public BigDecimal getBigDecimalOrDefault(String key, BigDecimal defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (!(returnable instanceof BigDecimal)) {
                if (returnable instanceof Number) {
                    returnable = new BigDecimal(returnable.toString());
                } else if (returnable instanceof String) {
                    returnable = new BigDecimal((String) returnable);
                }
            }

            return (BigDecimal) returnable;
        } else {
            return defaultValue;
        }
    }

    public Boolean getBoolean(String key) {
        Object returnable = this.get(key);
        if (returnable instanceof String) {
            returnable = Boolean.valueOf((String) returnable);
        }

        return (Boolean) returnable;
    }

    public Boolean getBooleanOrDefault(String key, boolean defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (returnable instanceof String) {
                returnable = Boolean.valueOf((String) returnable);
            }

            return (Boolean) returnable;
        } else {
            return defaultValue;
        }
    }

    public Byte getByte(String key) {
        Object returnable = this.get(key);
        if (returnable == null) {
            return null;
        } else {
            if (returnable instanceof String) {
                returnable = new BigDecimal((String) returnable);
            }

            return ((Number) returnable).byteValue();
        }
    }

    public Byte getByteOrDefault(String key, byte defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (returnable == null) {
                return null;
            } else {
                if (returnable instanceof String) {
                    returnable = new BigDecimal((String) returnable);
                }

                return ((Number) returnable).byteValue();
            }
        } else {
            return defaultValue;
        }
    }

    public <T extends Collection<?>> T getCollection(String key) {
        return (T) this.get(key);
    }

    public <T extends Collection<?>> T getCollectionOrDefault(String key, T defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            return (T) returnable;
        } else {
            return defaultValue;
        }
    }

    public Double getDouble(String key) {
        Object returnable = this.get(key);
        if (returnable == null) {
            return null;
        } else {
            if (returnable instanceof String) {
                returnable = new BigDecimal((String) returnable);
            }

            return ((Number) returnable).doubleValue();
        }
    }

    public Double getDoubleOrDefault(String key, double defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (returnable == null) {
                return null;
            } else {
                if (returnable instanceof String) {
                    returnable = new BigDecimal((String) returnable);
                }

                return ((Number) returnable).doubleValue();
            }
        } else {
            return defaultValue;
        }
    }

    public <T extends Enum<T>> T getEnum(String key) throws ClassNotFoundException {
        String value = this.getStringOrDefault(key, "");
        if (value == null) {
            return null;
        } else {
            String[] splitValues = value.split("\\.");
            int numberOfSplitValues = splitValues.length;
            StringBuilder returnTypeName = new StringBuilder();
            StringBuilder enumName = new StringBuilder();

            for (int i = 0; i < numberOfSplitValues; ++i) {
                if (i == numberOfSplitValues - 1) {
                    enumName.append(splitValues[i]);
                } else if (i == numberOfSplitValues - 2) {
                    returnTypeName.append(splitValues[i]);
                } else {
                    returnTypeName.append(splitValues[i]);
                    returnTypeName.append(".");
                }
            }

            Class<T> returnType = (Class<T>) Class.forName(returnTypeName.toString());
            T returnable = Enum.valueOf(returnType, enumName.toString());
            return returnable;
        }
    }

    public <T extends Enum<T>> T getEnumOrDefault(String key, T defaultValue) throws ClassNotFoundException {
        if (this.containsKey(key)) {
            String value = this.getStringOrDefault(key, "");
            if (value == null) {
                return null;
            } else {
                String[] splitValues = value.split("\\.");
                int numberOfSplitValues = splitValues.length;
                StringBuilder returnTypeName = new StringBuilder();
                StringBuilder enumName = new StringBuilder();

                for (int i = 0; i < numberOfSplitValues; ++i) {
                    if (i == numberOfSplitValues - 1) {
                        enumName.append(splitValues[i]);
                    } else if (i == numberOfSplitValues - 2) {
                        returnTypeName.append(splitValues[i]);
                    } else {
                        returnTypeName.append(splitValues[i]);
                        returnTypeName.append(".");
                    }
                }

                Class<T> returnType = (Class<T>) Class.forName(returnTypeName.toString());
                T returnable = Enum.valueOf(returnType, enumName.toString());
                return returnable;
            }
        } else {
            return defaultValue;
        }
    }

    public Float getFloat(String key) {
        Object returnable = this.get(key);
        if (returnable == null) {
            return null;
        } else {
            if (returnable instanceof String) {
                returnable = new BigDecimal((String) returnable);
            }

            return ((Number) returnable).floatValue();
        }
    }

    public Float getFloatOrDefault(String key, float defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (returnable == null) {
                return null;
            } else {
                if (returnable instanceof String) {
                    returnable = new BigDecimal((String) returnable);
                }

                return ((Number) returnable).floatValue();
            }
        } else {
            return defaultValue;
        }
    }

    public Integer getInteger(String key) {
        Object returnable = this.get(key);
        if (returnable == null) {
            return null;
        } else {
            if (returnable instanceof String) {
                returnable = new BigDecimal((String) returnable);
            }

            return ((Number) returnable).intValue();
        }
    }

    public Integer getIntegerOrDefault(String key, int defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (returnable == null) {
                return null;
            } else {
                if (returnable instanceof String) {
                    returnable = new BigDecimal((String) returnable);
                }

                return ((Number) returnable).intValue();
            }
        } else {
            return defaultValue;
        }
    }

    public Long getLong(String key) {
        Object returnable = this.get(key);
        if (returnable == null) {
            return null;
        } else {
            if (returnable instanceof String) {
                returnable = new BigDecimal((String) returnable);
            }

            return ((Number) returnable).longValue();
        }
    }

    public Long getLongOrDefault(String key, long defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (returnable == null) {
                return null;
            } else {
                if (returnable instanceof String) {
                    returnable = new BigDecimal((String) returnable);
                }

                return ((Number) returnable).longValue();
            }
        } else {
            return defaultValue;
        }
    }

    public <T extends Map<?, ?>> T getMap(String key) {
        return (T) this.get(key);
    }

    public <T extends Map<?, ?>> T getMapOrDefault(String key, T defaultValue) {
        Object returnable;
        if (this.containsKey(key)) {
            returnable = this.get(key);
        } else {
            returnable = defaultValue;
        }

        return (T) returnable;
    }

    public Short getShort(String key) {
        Object returnable = this.get(key);
        if (returnable == null) {
            return null;
        } else {
            if (returnable instanceof String) {
                returnable = new BigDecimal((String) returnable);
            }

            return ((Number) returnable).shortValue();
        }
    }

    public Short getShortOrDefault(String key, short defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (returnable == null) {
                return null;
            } else {
                if (returnable instanceof String) {
                    returnable = new BigDecimal((String) returnable);
                }

                return ((Number) returnable).shortValue();
            }
        } else {
            return defaultValue;
        }
    }

    public String getString(String key) {
        Object returnable = this.get(key);
        if (returnable instanceof Boolean) {
            returnable = returnable.toString();
        } else if (returnable instanceof Number) {
            returnable = returnable.toString();
        }

        return (String) returnable;
    }

    public String getStringOrDefault(String key, String defaultValue) {
        if (this.containsKey(key)) {
            Object returnable = this.get(key);
            if (returnable instanceof Boolean) {
                returnable = returnable.toString();
            } else if (returnable instanceof Number) {
                returnable = returnable.toString();
            }

            return (String) returnable;
        } else {
            return defaultValue;
        }
    }
}