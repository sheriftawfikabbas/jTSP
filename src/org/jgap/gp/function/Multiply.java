/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package org.jgap.gp.function;

import org.jgap.*;
import org.jgap.gp.*;
import org.jgap.gp.impl.*;
import org.jgap.util.*;

/**
 * The multiply operation.
 *
 * @author Klaus Meffert
 * @since 3.0
 */
public class Multiply
    extends MathCommand implements IMutateable, ICloneable {
  /** String containing the CVS revision. Read out via reflection!*/
  private static final String CVS_REVISION = "$Revision: 1.9 $";

  public Multiply(final GPConfiguration a_conf, Class a_returnType)
      throws InvalidConfigurationException {
    super(a_conf, 2, a_returnType);
  }

  public CommandGene applyMutation(int index, double a_percentage)
      throws InvalidConfigurationException {
    Divide mutant = new Divide(getGPConfiguration(), getReturnType());
    return mutant;
  }

  /**
   * Clones the object. Simple and straight forward implementation here.
   *
   * @return cloned instance of this object
   *
   * @author Klaus Meffert
   * @since 3.4
   */
  public Object clone_ICloneable() {
    try {
      Multiply result = new Multiply(getGPConfiguration(), getReturnType());
      return result;
    } catch (Exception ex) {
      throw new CloneException(ex);
    }
  }

  public String toString() {
    return "&1 * &2";
  }

  /**
   * @return textual name of this command
   *
   * @author Klaus Meffert
   * @since 3.2
   */
  public String getName() {
    return "Multiply";
  }

  public int execute_int(ProgramChromosome c, int n, Object[] args) {
    return c.execute_int(n, 0, args) * c.execute_int(n, 1, args);
  }

  public long execute_long(ProgramChromosome c, int n, Object[] args) {
    return c.execute_long(n, 0, args) * c.execute_long(n, 1, args);
  }

  public float execute_float(ProgramChromosome c, int n, Object[] args) {
    return c.execute_float(n, 0, args) * c.execute_float(n, 1, args);
  }

  public double execute_double(ProgramChromosome c, int n, Object[] args) {
    return c.execute_double(n, 0, args) * c.execute_double(n, 1, args);
  }

  public Object execute_object(ProgramChromosome c, int n, Object[] args) {
    return ( (Compatible) c.execute_object(n, 0, args)).execute_multiply(c.
        execute_object(n, 1, args));
  }

  protected interface Compatible {
    public Object execute_multiply(Object o);
  }
}
