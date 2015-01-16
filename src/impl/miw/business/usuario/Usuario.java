package impl.miw.business.usuario;

import impl.miw.presentation.passforgotten.PassForgottenAction;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Project;
import com.miw.model.User;
import com.miw.persistence.UserDataService;

/**
 * Clase que implementa las operaciones para la capa de Negocio de los usuarios
 * 
 * @author Pablo
 * 
 */
public class Usuario implements UserService {

    static final String ENGLISH = "EN";
    static final String SPANISH = "ES";
    static final String MANAGER = "Manager";

    private UserDataService userDataService;
    private LogService log;

    private final Properties properties = new Properties();
    private Session session;

    /**
     * Getter de la Infraestructura de Log
     * 
     * @return Log
     */
    public LogService getLog() {
	return log;
    }

    /**
     * Setter de la infraestructura de Log
     * 
     * @param log
     */
    public void setLog(LogService log) {
	this.log = log;
    }

    /**
     * Getter de la interfaz entre el negocio y la persistencia de datos de
     * usuarios
     * 
     * @return UserDataService interfaz entre el negocio y la persistencia
     */
    public UserDataService getUserDataService() {
	return userDataService;
    }

    /**
     * Setter de la interfaz entre el negocio y la persistencia de datos de
     * usuarios
     * 
     * @param userDataService
     *            interfaz entre el negocio y la persistencia
     */
    public void setUserDataService(UserDataService userDataService) {
	this.userDataService = userDataService;
    }

    /**
     * Getter del método de la interfaz entre presentación y negocio para
     * obtener los usuarios
     * 
     * @return Vector<User> vector con usuarios
     * @throws Exception
     */
    @Override
    public Vector<User> getUsers() throws Exception {
	log.debug("Entrando en getUsers");
	return userDataService.getUsers();
    }

    /**
     * Setter del método de la interfaz entre presentación y negocio para
     * establecer los usuarios
     * 
     * @param user
     *            usuario
     * @return String valor devuelto para comprobar la inserción
     * @throws Exception
     */
    @Override
    public String setUser(User user) throws Exception {
	log.debug("Entrando en setUser");
	return userDataService.setUser(user);
    }

    /**
     * Getter del método de la interfaz entre presentación y negocio para
     * obtener la contraseña
     * 
     * @param passForgottenAction
     *            el action con los datos
     * @return Boolean valor booleano de que se ha encontrado el suaurio y su
     *         contraseña
     * @throws Exception
     */
    @Override
    public Boolean getPassForgotten(PassForgottenAction passForgottenAction)
	    throws Exception {
	log.debug("Entrando en setPassForgotten");
	User user = userDataService.getPassForgotten(passForgottenAction);

	// Cargar JavaMail
	if (user != null) {
	    makeJavamail(user, 0, null);
	} else {
	    return false;
	}
	return true;
    }

    /**
     * Método de la interfaz entre presentación y negocio para el envío de
     * correo
     * 
     * @param user
     *            usuario
     * @param i
     *            opción de tipo de envio
     * @param pro
     *            proyecto
     * @throws MessagingException
     */
    @Override
    public void send(User user, int optionSend, int i, Project pro)
	    throws MessagingException {
	switch (optionSend) {
	case 1:
	    makeJavamail(user, i, pro);
	    break;
	case 2:
	    // SMS();
	    break;
	case 3:
	    // mensakeriaInterna();
	    break;
	case 4:
	    // whatsApp();
	    break;
	case 5:
	    // facebook();
	    break;
	default:
	    break;
	}
    }

    /**
     * Método para la creación de Javamail
     * 
     * @param user
     *            usuario
     * @param op
     *            opción de tipo de envio
     * @param pro
     *            proyecto
     * @throws MessagingException
     */
    private void makeJavamail(User user, Integer op, Project pro)
	    throws MessagingException {
	init();
	switch (op) {
	case 0:
	    try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress((String) properties
			.get("mail.smtp.mail.sender")));
		message.addRecipient(Message.RecipientType.TO,
			new InternetAddress(user.getEmail()));
		if (user.getLanguage().equalsIgnoreCase(ENGLISH)) {
		    message.setSubject(ResourceBundle.getBundle("global_en")
			    .getString("correo.subject_pass"));
		    message.setText(ResourceBundle.getBundle("global_en")
			    .getString("correo.text_pass") + user.getPassword());
		} else {
		    message.setSubject(ResourceBundle.getBundle("global")
			    .getString("correo.subject_pass"));
		    message.setText(ResourceBundle.getBundle("global")
			    .getString("correo.text_pass") + user.getPassword());
		}
		Transport t = session.getTransport("smtp");
		t.connect((String) properties.get("mail.smtp.user"), "tfm_2014");
		t.sendMessage(message, message.getAllRecipients());
		t.close();
	    } catch (MessagingException me) {
		throw (me);
	    }
	    break;

	case 1:
	    try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress((String) properties
			.get("mail.smtp.mail.sender")));
		message.addRecipient(Message.RecipientType.TO,
			new InternetAddress(user.getEmail()));
		if (user.getLanguage().contains(ENGLISH)) {
		    message.setSubject(ResourceBundle.getBundle("global_en")
			    .getString("correo.subject_new"));
		    message.setText(ResourceBundle.getBundle("global_en")
			    .getString("correo.text_new_1")
			    + user.getLogin()
			    + "\n"
			    + ResourceBundle.getBundle("global_en").getString(
				    "correo.text_new_2")
			    + user.getEmail()
			    + "\n"
			    + ResourceBundle.getBundle("global_en").getString(
				    "correo.text_new_3")
			    + user.getPassword()
			    + "\n"
			    + ResourceBundle.getBundle("global_en").getString(
				    "correo.text_new_4")
			    + MANAGER
			    + "\n"
			    + ResourceBundle.getBundle("global_en").getString(
				    "correo.text_new_5") + user.getLanguage());
		} else {
		    message.setSubject(ResourceBundle.getBundle("global")
			    .getString("correo.subject_new"));
		    message.setText(ResourceBundle.getBundle("global")
			    .getString("correo.text_new_1")
			    + user.getLogin()
			    + "\n"
			    + ResourceBundle.getBundle("global").getString(
				    "correo.text_new_2")
			    + user.getEmail()
			    + "\n"
			    + ResourceBundle.getBundle("global").getString(
				    "correo.text_new_3")
			    + user.getPassword()
			    + "\n"
			    + ResourceBundle.getBundle("global").getString(
				    "correo.text_new_4")
			    + MANAGER
			    + "\n"
			    + ResourceBundle.getBundle("global").getString(
				    "correo.text_new_5") + user.getLanguage());
		}
		Transport t = session.getTransport("smtp");
		t.connect((String) properties.get("mail.smtp.user"), "tfm_2014");
		t.sendMessage(message, message.getAllRecipients());
		t.close();
	    } catch (MessagingException me) {
		throw (me);
	    }
	    break;
	case 2:
	    try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress((String) properties
			.get("mail.smtp.mail.sender")));
		message.addRecipient(Message.RecipientType.TO,
			new InternetAddress(user.getEmail()));
		if (user.getLanguage().contains(ENGLISH)) {
		    message.setSubject(ResourceBundle.getBundle("global")
			    .getString("correo.subject_create"));
		    message.setText(ResourceBundle.getBundle("global")
			    .getString("correo.text_create_1")
			    + pro.getNombre()
			    + "\n"
			    + ResourceBundle.getBundle("global").getString(
				    "correo.text_create_2")
			    + pro.getFecha()
			    + "\n"
			    + ResourceBundle.getBundle("global").getString(
				    "correo.text_create_4") + pro.getPaso());
		} else {
		    message.setSubject(ResourceBundle.getBundle("global")
			    .getString("correo.subject_create"));
		    message.setText(ResourceBundle.getBundle("global")
			    .getString("correo.text_create_1")
			    + pro.getNombre()
			    + "\n"
			    + ResourceBundle.getBundle("global").getString(
				    "correo.text_create_2")
			    + pro.getFecha()
			    + "\n"
			    + ResourceBundle.getBundle("global").getString(
				    "correo.text_create_4") + pro.getPaso());
		}
		Transport t = session.getTransport("smtp");
		t.connect((String) properties.get("mail.smtp.user"), "tfm_2014");
		t.sendMessage(message, message.getAllRecipients());
		t.close();
	    } catch (MessagingException me) {
		throw (me);
	    }
	    break;
	}
    }

    /**
     * Método de carga de parámetros iniciales para el envío de correos mediante
     * Gmail
     */
    private void init() {
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.starttls.enable", "true");
	properties.put("mail.smtp.port", 587);
	properties.put("mail.smtp.mail.sender", "riskmanagementtfm@gmail.com");
	properties.put("mail.smtp.user", "riskmanagementtfm");
	properties.put("mail.smtp.auth", "true");

	session = Session.getDefaultInstance(properties);
    }

    /**
     * Setter del método de la interfaz entre presentación y negocio para
     * establecer la actualización de usuarios
     * 
     * @param user
     *            usuario
     * @return String valor devuelto para el log
     * @throws Exception
     */
    @Override
    public String setUpdateUser(User user) throws Exception {
	log.debug("Entrando en setUpdateUser");
	return userDataService.setUpdateUser(user);
    }

    /**
     * Getter del método de la interfaz entre presentación y negocio para
     * obtener los managers
     * 
     * @return Vector<User> vector con usuarios managers
     * @throws Exception
     */
    @Override
    public Vector<User> getManagers() throws Exception {
	log.debug("Entrando en getManagers");
	return userDataService.getManagers();
    }

    /**
     * Método de la interfaz entre presentación y negocio para la busqueda de
     * usuarios por medio del email
     * 
     * @param email
     *            correo
     * @return User usuario
     * @throws Exception
     */
    @Override
    public User seekUser(String email) throws Exception {
	log.debug("Entrando en seekUser");
	return userDataService.getUser(email);
    }

    /**
     * Getter del método de la interfaz entre presentación y negocio para
     * obtener los usuarios por medio del proyecto
     * 
     * @param idProyecto
     *            identificación de proyecto
     * @return Vector<User> vector con usuarios
     * @throws Exception
     */
    @Override
    public Vector<User> getUsers(Long idProyecto) throws Exception {
	log.debug("Entrando en getUsers");
	return userDataService.getUsers(idProyecto);
    }

    /**
     * Método de la interfaz entre presentación y negocio para eliminar el
     * usuario
     * 
     * @param idUser
     *            identificación de usuario
     * @return Booelan de comprobación para el borrado
     * @throws Exception
     */
    @Override
    public Boolean deleteUser(Long idUser) throws Exception {
	log.debug("Entrando en deleteUser");
	return userDataService.deleteUser(idUser);
    }

    /**
     * Método de la interfaz entre presentación y negocio para la actualización
     * de datos de admin
     * 
     * @param updateUser
     *            usuario a actualizar
     * @return String valor devuelto para el log
     * @throws Exception
     */
    @Override
    public String setUpdateUserAdmin(User updateUser) throws Exception {
	log.debug("Entrando en setUpdateUserAdmin");
	return userDataService.setUpdateUserAdmin(updateUser);
    }

    /**
     * Método de la interfaz entre presentación y negocio para la busqueda de
     * usuarios por medio del id
     * 
     * @param idUserUpdate
     *            identificador del usuario
     * @param idProject
     *            identificador del proyecto
     * @return Boolean true si existe o false sino no
     * @throws Exception
     */
    @Override
    public boolean getManager(Long idUserUpdate, Long idProject)
	    throws Exception {
	log.debug("Entrando en getManager");
	return userDataService.getManager(idUserUpdate, idProject);
    }
}
