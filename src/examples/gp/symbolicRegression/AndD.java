/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package examples.gp.symbolicRegression;

import org.jgap.*;
import org.jgap.gp.*;
import org.jgap.gp.impl.*;
import org.jgap.util.*;

/**
 * The boolean and operation.
 *
 * @author Klaus Meffert, Hakan Kjellerstrand
 * @since 3.5
 */
public class AndD
    extends MathCommand implements IMutateable, ICloneable {
  /** String containing the CVS revision. Read out via reflection!*/
  private final static String CVS_REVISION = "$Revision: 1.1 $";

  public AndD(final GPConfiguration a_conf)
      throws InvalidConfigurationException {
    this(a_conf, CommandGene.DoubleClass);
  }

  public AndD(final GPConfiguration a_conf, Class a_returnType)
      throws InvalidConfigurationException {
    super(a_conf, 2, a_returnType);
  }

  public CommandGene applyMutation(int index, double a_percentage)
      throws InvalidConfigurationException {
    CommandGene mutant;
    if (a_percentage < 0.5d) {
      mutant = new XorD(getGPConfiguration());
    }
    else {
      mutant = new OrD(getGPConfiguration());
    }
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
      AndD result = new AndD(getGPConfiguration(), getReturnType());
      return result;
    } catch (Exception ex) {
      throw new CloneException(ex);
    }
  }

  public String toString() {
    return "&1 && &2";
  }

  /**
   * @return textual name of this command
   *
   * @author Klaus Meffert
   * @since 3.2
   */
  public String getName() {
    return "AndD";
  }

  public double execute_double(ProgramChromosome c, int n, Object[] args) {
    double d1 = c.execute_double(n, 0, args);
    double d2 = c.execute_double(n, 1, args);
    boolean b1 = true;
    boolean b2 = true;
    if (d1 < 1.0d) {
      b1 = false;
    }
    if (d2 < 1.0d) {
      b2 = false;
    }
    if (b1 & b2) {
      return 1.0d;
    }
    else {
      return 0.0d;
    }
  }
}
