/*
 * 创建日期 2005-8-23
 */
package com.xiaobo.springboot2.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import java.rmi.RemoteException;

/**
 * @author lei_peng
 */
public class SessionFacade {
	private Context context;
//	private LoginLocalBD loginFacade;
	// private LoginHome loginHome;
	private String cookieDomain;
	private boolean cookieUseDomain;
	private HttpServletRequest request;

	protected static Log log = LogFactory.getLog(SessionFacade.class);

	public static String getLoginUrl() {
		if (IS_DEPLOY)
			return "/login.jsp";
		else
			return "/auth/login.jsp";
	}

	public final String getLoginPage() {
		return getLoginUrl();
	}

	/**
	 * @return 返回 loginUser。
	 */
	public final String getLoginUser() {
		return "lbt";
	}



	private static boolean IS_DEPLOY = true;

	/*public SessionFacade(HttpServletRequest req) throws Exception {
		FileInputStream input = null;
		try {
			Properties props = new Properties();
			if (IS_DEPLOY) {
				props.put(Context.INITIAL_CONTEXT_FACTORY, TSMResourceManage
						.getResourceForFileNameAndKey(
								TSMConstant.AUTH_JNDI_RESOURCE_FILE,
								"java.naming.factory.initial"));
				props.put(Context.PROVIDER_URL, TSMResourceManage
						.getResourceForFileNameAndKey(
								TSMConstant.AUTH_JNDI_RESOURCE_FILE,
								"java.naming.provider.url"));
				props.put("cookie.domain", TSMResourceManage
						.getResourceForFileNameAndKey(
								"../../../../conf/tsm.properties",
								"cookie.domain"));
				props.put("cookie.useDomain", TSMResourceManage
						.getResourceForFileNameAndKey(
								"../../../../conf/tsm.properties",
								"cookie.useDomain"));
				System.out
						.println("===================部署=======================");
			} else {
				String fileName = "C:/temp/jndi.properties";
				input = new FileInputStream(new File(fileName));
				props.load(input);
				System.out
						.println("===================自测=======================");
			}
			cookieDomain = (String) props.get("cookie.domain");
			cookieUseDomain = Boolean.valueOf(
					(String) props.get("cookie.useDomain")).booleanValue();
			context = new InitialContext(props);
			request = req;
			userFacade = (UmFacadeLocalBD) BDFactory.getInstance().getBD(
					com.topsec.tsm.auth.manage.bd.UmFacadeLocalBD.class, req,
					"1111");
			roleFacade = (RmFacadeLocalBD) BDFactory.getInstance().getBD(
					com.topsec.tsm.auth.manage.bd.RmFacadeLocalBD.class, req,
					"1111");
			loginFacade = (LoginLocalBD) BDFactory.getInstance().getBD(
					com.topsec.tsm.security.bd.LoginLocalBD.class, req, "1111");
			System.out
					.println(">>>>>>>>>>>>>>>>>>>>>>>> Login is ready >>>>>>>>>>>>>>>>>>>>>>>>");
		} finally {
			if (input != null)
				input.close();
		}
	}

	public RoleForm getRole(int id) throws AuthException, RemoteException,
			UnknownHostException {
		AuthRole data = roleFacade.getRoleByID(id, sid);
		RoleForm form = new RoleForm();
		form.setByEjb(data);
		return form;
	}

	public void queryRole(int id, RoleForm form) throws AuthException,
			RemoteException, UnknownHostException {
		AuthRole data = roleFacade.getRoleByID(id, sid);
		form.setByEjb(data);
	}

	// 增加一个角色组
	public Group addRoleGroup(Group group, long parentid) throws AuthException,
			RemoteException, UnknownHostException {
		Group data = roleFacade.addRoleGroup(group, parentid, sid);
		// GroupForm groupfrom = new GroupForm();
		// groupfrom.setGroupForm(data);
		return data;
	}

	// 删除一个角色组
	public void delteRoleGroup(long id) throws AuthException, RemoteException,
			UnknownHostException {
		roleFacade.delteRoleGroup(id, sid);
	}

	// 修改一个角色组
	public Group updateRoleGroup(Group group) throws AuthException,
			RemoteException, UnknownHostException {
		Group data = roleFacade.updateRoleGroup(group, sid);
		// GroupForm groupfrom = new GroupForm();
		// groupfrom.setGroupForm(data);
		return data;
	}

	// 查询一个角色组
	public Group queryRoleGroup(long id) throws AuthException, RemoteException,
			UnknownHostException {
		Group data = roleFacade.queryRoleGroup(id, sid);
		// GroupForm groupfrom = new GroupForm();
		// groupfrom.setGroupForm(data);
		return data;
	}

	public UserForm getUser(int id) throws AuthException, RemoteException {
		AuthAccount data = userFacade.getUserByID(id, sid);
		UserForm form = new UserForm();
		form.setByEjb(data);
		return form;
	}

	public void queryUser(UserForm form) throws AuthException, RemoteException,
			UnknownHostException {
		AuthAccount data = userFacade.getUserByID(form.getId().intValue(), sid);
		form.setByEjb(data);
	}

	public Collection getAllRoles() throws AuthException, RemoteException {
		return roleFacade.getAllRoles(sid);
	}

	public Collection getUsers(RoleForm role) throws AuthException,
			RemoteException {
		return roleFacade.getAllUsers(role.getId().intValue(), sid);
	}

	public AuthRole addRole(RoleForm role) throws AuthException,
			RemoteException, UnknownHostException {
		AuthRole data = new AuthRole();
		role.queryEjb(data);
		data.setId(null);
		data = roleFacade.addRole(data, sid);
		role.setByEjb(data);
		return data;
	}

	public void addUser(UserForm user) throws AuthException, RemoteException {
		AuthAccount account = new AuthAccount();
		user.queryEjb(account);
		account.setID(null);
		account = userFacade.addUser(account, sid);
		user.setByEjb(account);
	}

	public void deleteRole(RoleForm role) throws AuthException, RemoteException {
		Collection roles = new ArrayList(1);
		roles.add(role);
		roleFacade.delRoles(roles, sid);
	}

	public void deleteRole(int roleId) throws AuthException, RemoteException {
		Collection roles = new ArrayList(1);
		AuthRole role = roleFacade.getRoleByID(roleId, sid);
		roles.add(role);
		roleFacade.delRoles(roles, sid);
	}

	public void deleteUser(int userId) throws AuthException, RemoteException {
		userFacade.delUserByID(userId, sid);
	}

	public void updateRole(RoleForm role) throws AuthException, RemoteException {
		AuthRole ejbRole = roleFacade.getRoleByID(role.getId().intValue(), sid);
		role.queryEjb(ejbRole);
		roleFacade.modifyRole(ejbRole, sid);
	}

	public AuthAccount updateUser(UserForm user) throws AuthException, RemoteException {
		AuthAccount account = new AuthAccount();
		user.queryEjb(account);
		account = userFacade.ModifyInfo(account, sid);
		user.setByEjb(account);
		return  account;
	}

	public void login(String loginName, String password, InetAddress addr)
			throws AuthException, RemoteException {

		sid = loginFacade.login(loginName, password, addr);
		loginUser = loginName;
		if (sid == null) {
			throw new RuntimeException(loginFacade + ".login() 返回了 null");
		}
		request.getSession().setAttribute("CC_SID", sid.toString());
	}

	public void logout() throws AuthException, RemoteException {
		loginFacade.logout(sid);
	}

	public ResourceTree getResourceTree() throws AuthException, RemoteException {
		return new ResourceTree(roleFacade.getAllResources(sid));
	}

	public Map queryPermissionMap(int roleId) throws AuthException,
			RemoteException {
		AuthRole role = roleFacade.getRoleByID(roleId, sid);
		Set set = role.getPermissions();
		Map map = new HashMap();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			AuthPermission ap = (AuthPermission) iter.next();
			map.put(ap.getResourceUrl(), Permission.getFlyWeight(ap
					.getActions().intValue()));
		}
		return map;
	}

	public void updatePermissionMap(int roleId, Map map) throws AuthException,
			RemoteException {
		AuthRole role = roleFacade.getRoleByID(roleId, sid);
		Set set = new HashSet();
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			Permission p = (Permission) entry.getValue();
			AuthPermission ap = new AuthPermission();
			ap.setResourceUrl((String) entry.getKey());
			ap.setActions(new Integer(p.getFlags()));
		}
		role.setPermissions(set);
		roleFacade.modifyRole(role, sid);
	}

	*//*
	 * TA Version Up 3.9 Add by 邢治理 -- /Start/ 2010/05/19 Reason:
	 * 用户可以从子模块查看和修改个人信息
	 *//*
	*//**
	 * 功能 用户可以从子模块修改个人信息
	 * 
	 * @param uf
	 *            是用户表单对象
	 * @param userId
	 *            是用户的id
	 *//*
	public void updateUserInfo(UserForm uf) throws AuthException,
			RemoteException {
		AuthAccount data = new AuthAccount();
		UserForm uf1 = new UserForm();
		uf1.setId(uf.getId());

		// add by 李学宇 start
		Date now = new Date();
		Timestamp timestamp = new Timestamp(now.getTime());
		uf1.setLastModifyTime(timestamp);
		// add by 李学宇 end
		this.lookupUserInfo(uf1);
		if (uf1.getPassword() == null)
			throw new AuthException("用户不存在");
		if (!uf1.getPassword().equals(uf.getOldPassword()))
			throw new AuthException("旧密码不正确");
		uf1 = null;
		uf.queryEjb(data);
		data = userFacade.updateUserInfo(data, sid);
		uf.setByEjb(data);
	}

	*//**
	 * 非systemAdmin用户也可以修改自己的部分信息--孙祖凤
	 * @param uf
	 * @throws AuthException
	 * @throws RemoteException
	 *//*
	public void updateUserInfoBySelf(UserForm uf) throws AuthException,
			RemoteException {
		AuthAccount data = new AuthAccount();
		UserForm uf1 = new UserForm();
		uf1.setId(uf.getId());

		// add by 李学宇 start
		Date now = new Date();
		Timestamp timestamp = new Timestamp(now.getTime());
		uf1.setLastModifyTime(timestamp);
		// add by 李学宇 end
		this.lookupUserInfo(uf1);
		if (uf1.getPassword() == null)
			throw new AuthException("用户不存在");
//		if(null!=passwordEdit && "yes".equals(passwordEdit)){
//			if( !uf1.getPassword().equals(uf.getOldPassword())){
//				throw new AuthException("旧密码不正确");
//			}
//			if (uf1.getPassword().equals(uf.getPassword())){
//				throw new AuthException("新密码不能与旧密码相同！");
////				request.setAttribute("message", "");
//			}
//			try {
//				PasswordPolicySupport passwordPolicySupport = new PasswordPolicySupport();
//				passwordPolicySupport.inspectPassword(
//						TSMConstant.PASSWORD_POLICY_FILE, DecryptUtils.desEncrypt(uf.getPassword()));
//			} catch (InspectionException e) {
//				throw new AuthException(e.getMessage());
//			}
//		}

		uf1 = null;
		uf.queryEjb(data);
//		data = userFacade.updateUserInfo(data, sid);
		data = userFacade.updateUserInfoBySelf(data, sid);
		uf.setByEjb(data);
	}

	*//**
	 * 功能 用户可以从子模块查看个人信息
	 * 
	 * @param uf
	 *            是用户表单对象
	 * @param userId
	 *            是用户的id
	 *//*
	public void lookupUserInfo(UserForm uf) throws AuthException,
			RemoteException {
		AuthAccount data = userFacade
				.lookupUserInfo(uf.getId().intValue(), sid);
		uf.setByEjb(data);
	}

	*//*
	 * Add by 邢治理 -- /End/
	 *//*
	public int getRoleId(int accountId) {
		int roleId = 0;
		org.hibernate.classic.Session _session = null;

		try {
			Context context = new InitialContext();
			SessionFactory sessionFactory = (SessionFactory)context.lookup("java:/hibernate/SessionFactory");
			_session = sessionFactory.openSession();
			String sql = "select role_id  as roleId from auth_user_role where account_id=" + accountId;
			SQLQuery sqlQuery = _session.createSQLQuery(sql);
			roleId = ((Integer)sqlQuery.addScalar("roleId", Hibernate.INTEGER).uniqueResult()).intValue();
		} catch (Exception var11) {
			var11.printStackTrace();
		} finally {
			if(_session != null && _session.isOpen()) {
				_session.close();
			}

		}
		return roleId;
	}
	public final SID getSid() {
		return sid;
	}

	public final void setSid(String sidText) {
		this.sid = new SID(sidText);
		this.loginUser = sid.getName();
	}

	private String loginUser;

	private UmFacadeLocalBD userFacade;
	private RmFacadeLocalBD roleFacade;

	private SID sid;

	public static final boolean isIS_DEPLOY() {
		return IS_DEPLOY;
	}

	public final String getCookieDomain() {
		return cookieDomain;
	}

	public final boolean isCookieUseDomain() {
		return cookieUseDomain;
	}*/
}
