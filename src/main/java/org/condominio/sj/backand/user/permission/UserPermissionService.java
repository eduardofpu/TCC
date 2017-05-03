package org.condominio.sj.backand.user.permission;


import org.condominio.sj.backand.z.utils.GenericService;
import org.condominio.sj.backand.z.utils.ServicePath;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath. USER_PERMISSION_PATH )
public class UserPermissionService extends GenericService<UserPermissionEntity, UserPermissionKey>{

}
