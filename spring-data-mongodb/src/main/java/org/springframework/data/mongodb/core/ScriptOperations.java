/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.mongodb.core;

import java.util.Set;

import org.springframework.data.mongodb.core.script.CallableMongoScript;
import org.springframework.data.mongodb.core.script.ServerSideJavaScript;

import com.mongodb.DB;

/**
 * Script operations on {@link com.mongodb.DB} level. Allows interaction with server side {@literal JavaScript}
 * functions.
 * 
 * @author Christoph Strobl
 * @since 1.7
 */
public interface ScriptOperations {

	/**
	 * Store given {@literal script} to {@link com.mongodb.DB} so it can be called via its name.
	 * 
	 * @param script must not be {@literal null}.
	 * @return {@link CallableMongoScript} with name under which the {@literal JavaScript} function can be called.
	 */
	CallableMongoScript register(ServerSideJavaScript script);

	/**
	 * Executes the {@literal script} by either calling it via its {@literal name} or directly sending it.
	 * 
	 * @param script must not be {@literal null}.
	 * @param args arguments to pass on for script execution.
	 * @return the script evaluation result.
	 * @throws org.springframework.dao.DataAccessException
	 */
	Object execute(ServerSideJavaScript script, Object... args);

	/**
	 * Call the {@literal JavaScript} by its name.
	 * 
	 * @param scriptName must not be {@literal null} or empty.
	 * @param args
	 * @return
	 */
	Object call(String scriptName, Object... args);

	/**
	 * Checks {@link DB} for existence of {@link ServerSideJavaScript} with given name.
	 * 
	 * @param scriptName must not be {@literal null} or empty.
	 * @return false if no {@link ServerSideJavaScript} with given name exists.
	 */
	Boolean exists(String scriptName);

	/**
	 * Returns names of {@literal JavaScript} functions that can be called.
	 * 
	 * @return empty {@link Set} if no scripts found.
	 */
	Set<String> scriptNames();

}