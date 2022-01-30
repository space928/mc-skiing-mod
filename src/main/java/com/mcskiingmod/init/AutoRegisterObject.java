package com.mcskiingmod.init;

import com.mcskiingmod.items.IRegisterable;

public @interface AutoRegisterObject
{
    RegisterableObjectType objType();
    IRegisterable regInstance();
}
