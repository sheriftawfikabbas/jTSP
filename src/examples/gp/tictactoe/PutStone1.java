/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package examples.gp.tictactoe;

import org.jgap.gp.*;
import org.jgap.*;
import org.jgap.gp.impl.*;
import org.jgap.util.*;

public class PutStone1
    extends CommandGene implements ICloneable {
  /** String containing the CVS revision. Read out via reflection!*/
  private final static String CVS_REVISION = "$Revision: 1.3 $";

  private Board m_board;

  private int m_color;

  public PutStone1(final GPConfiguration a_conf, Board a_board, int a_color)
      throws InvalidConfigurationException {
    this(a_conf, a_board, a_color, 0, 0);
  }

  public PutStone1(final GPConfiguration a_conf, Board a_board, int a_color,
                   int a_subReturnType, int a_subChildType)
      throws InvalidConfigurationException {
    super(a_conf, 1, CommandGene.VoidClass, a_subReturnType, a_subChildType);
    m_board = a_board;
    m_color = a_color;
  }

  public String toString() {
    return "put_stone1(&1)";
  }

  public void execute_void(ProgramChromosome c, int n, Object[] args) {
    check(c);
    int index = c.execute_int(n, 0, args);
    int x = index / Board.WIDTH;
    int y = index % Board.HEIGHT;
    // Put stone on board.
    // -------------------
    boolean gameWon = m_board.putStone(x, y, m_color);
    // If game won, quit GP-program.
    // -----------------------------
    if (gameWon) {
      throw new GameWonException(m_color, "Game won by color " + m_color);
    }
  }

  protected void check(ProgramChromosome a_program) {
    if (m_board.getLastColor() == m_color) {
      throw new IllegalStateException("Only one stone of a color per round!");
    }
  }

  /**
   * Determines which type a specific child of this command has.
   *
   * @param a_ind ignored here
   * @param a_chromNum index of child
   * @return type of the a_chromNum'th child
   *
   * @author Klaus Meffert
   * @since 3.2
   */
  public Class getChildType(IGPProgram a_ind, int a_chromNum) {
    return CommandGene.IntegerClass;
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
      PutStone1 result = new PutStone1(getGPConfiguration(), m_board, m_color,
                                       getSubReturnType(), getSubChildType(0));
      return result;
    } catch (Throwable t) {
      throw new CloneException(t);
    }
  }
}
