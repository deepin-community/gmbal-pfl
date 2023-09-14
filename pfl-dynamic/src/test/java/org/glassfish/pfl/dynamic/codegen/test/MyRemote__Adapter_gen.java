/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2018 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.pfl.dynamic.codegen.test;

import org.glassfish.pfl.dynamic.codegen.spi.ClassGenerator;
import org.glassfish.pfl.dynamic.codegen.spi.Expression;
import org.glassfish.pfl.dynamic.codegen.ClassGeneratorFactory;
import static java.lang.reflect.Modifier.* ;
import static org.glassfish.pfl.dynamic.codegen.spi.Wrapper.* ;

public class MyRemote__Adapter_gen implements ClassGeneratorFactory {
    @Override
    public String className() {
	return "MyRemote__Adapter" ;
    }
    
    private Expression makeNewEJBException( String varName ) {
	return _new( _t("EJBException"), _s(_void(),_t("Exception")), _v(varName)) ;
    }

    public ClassGenerator evaluate() {
	_clear() ;
	_package( "dynamic.codegen.gen" ) ;
	_import( "java.lang.Exception" ) ;
	_import( "java.lang.Throwable" ) ;
	_import( "java.io.Serializable" ) ;
	_import( "javax.ejb.EJBObject" ) ;
	_import( "javax.ejb.EJBException" ) ;
	_import( "java.rmi.RemoteException" ) ;
	_import( "org.glassfish.pfl.dynamic.codegen.lib.AppException" ) ;
	_import( "org.glassfish.pfl.dynamic.codegen.lib.MyBusinessIntf" ) ;
	_import( "org.glassfish.pfl.dynamic.codegen.lib.EJBObjectBase" ) ;
	_import( "dynamic.codegen.gen.MyRemote" ) ;

	_class( PUBLIC, className(), _t("EJBObjectBase"),
	    _t("EJBObject"), _t("MyBusinessIntf"), _t("Serializable")) ;
	    
	    _data( PRIVATE, _t("MyRemote"), "myRemote" ) ;

	    _constructor( PUBLIC ) ;
		_arg( _t("MyRemote"), "arg" ) ;
	    _body() ;
		_expr(_super(_s(_void()))) ;
		_assign(_v("myRemote"),_v("arg")) ;
	    _end() ;

	    _method( PUBLIC, _void(), "doSomething" ) ;
	    _body() ;
		_try() ;
		    _expr( _call( _v("myRemote"), "doSomething", _s(_void()))) ;
		_catch( _t("RemoteException"), "re" ) ;
		    _throw( makeNewEJBException( "re" ) ) ;
		_end() ;
	    _end() ;

	    _method( PUBLIC, _int(), "doSomethingElse", _t("AppException")) ;
	    _body() ;
		_try() ;
		    _return( _call( _v("myRemote"), "doSomethingElse", _s(_int()))) ;
		_catch( _t("RemoteException"), "re" ) ;
		    _define( _t("EJBException"), "exc", 
			_new( _t("EJBException"), _s(_void()))) ;
		    _expr( _call( _v("exc"), "initCause", 
			_s(_t("Throwable"),_t("Throwable")), _v("re"))) ;
		    _throw( _v("exc")) ;
		_end() ;
	    _end() ;

	    _method( PUBLIC, _int(), "echo") ;
		_arg( _int(), "arg" ) ;
	    _body() ;
		_try() ;
		    _return( _call( _v("myRemote"), "echo", _s(_int(),_int()), _v("arg"))) ;
		_catch( _t("RemoteException"), "re" ) ;
		    _throw( makeNewEJBException( "re" ) ) ;
		_end() ;
	    _end() ;
	_end() ;

	return _classGenerator() ;
    }
}
