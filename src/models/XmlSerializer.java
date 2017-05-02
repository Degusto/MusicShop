package models;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Xml serialization and deserialization
 */
public interface XmlSerializer {
    /**
     * Serializes object
     * @param path path to save xml file
     * @param object object to serialize
     * @throws JAXBException JAXBException
     */
    default void serialize(final String path, final Object object) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(object, new File(path));
    }

    /**
     * Deserializes object from xml
     * @param path xml file path
     * @param type object type
     * @param <T> object type
     * @return product
     * @throws JAXBException JAXBException
     */
    default <T> T deserialize(final String path, final Class<T> type) throws JAXBException {
        final File file = new File(path);
        final JAXBContext jaxbContext = JAXBContext.newInstance(type);

        //noinspection unchecked
        return (T)jaxbContext.createUnmarshaller().unmarshal(file);
    }
}
