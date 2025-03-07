/*
 * Copyright (c) 2011 ymnk, JCraft,Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 *
 * 3. The names of the authors may not be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL JCRAFT, INC. OR ANY CONTRIBUTORS TO THIS SOFTWARE BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jcraft.jsch;

import java.util.Vector;

public class AgentIdentityRepository implements IdentityRepository {

  private AgentProxy agent;

  public AgentIdentityRepository(AgentConnector connector) {
    this.agent = new AgentProxy(connector);
  }

  @Override
  public Vector<?> getIdentities() {
    return agent.getIdentities();
  }

  @Override
  public boolean add(byte[] identity) {
    return agent.addIdentity(identity);
  }

  @Override
  public boolean remove(byte[] blob) {
    return agent.removeIdentity(blob);
  }

  @Override
  public void removeAll() {
    agent.removeAllIdentities();
  }

  @Override
  public String getName() {
    return agent.getConnector().getName();
  }

  @Override
  public int getStatus() {
    if (agent.getConnector().isAvailable()) {
      return RUNNING;
    } else {
      return NOTRUNNING;
    }
  }
}
