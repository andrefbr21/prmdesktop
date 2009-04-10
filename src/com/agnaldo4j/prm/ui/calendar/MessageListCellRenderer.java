/**
 * Copyright (c) 2006, Sun Microsystems, Inc
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following 
 *     disclaimer in the documentation and/or other materials provided 
 *     with the distribution.
 *   * Neither the name of the TimingFramework project nor the names of its
 *     contributors may be used to endorse or promote products derived 
 *     from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.agnaldo4j.prm.ui.calendar;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.bo.pessoa.PessoaFisica;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author sky
 */
public class MessageListCellRenderer implements ListCellRenderer {
    // Image panel size
    private static final int IS = 48;
    // Color for the from label
    private static final Color FROM_COLOR = new Color(0,  81, 212);
    // Alternating row color
    static final Color STRIPE_COLOR = new Color(237, 242, 249);
    
    private final RendererPanel panel;
    
    public MessageListCellRenderer() {
        panel = new RendererPanel();
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof String) {
            panel.setMessage(null);
        } else {
            panel.setMessage((Conversa)value);
        }
        if (isSelected) {
            adjustColors(list.getSelectionBackground(), list.getSelectionForeground());
        } else {
            adjustColors(list.getBackground(), list.getForeground());
            if ((index % 2) == 0) {
                panel.setBackground(STRIPE_COLOR);
            }
            panel.fromLabel.setForeground(FROM_COLOR);
            for (Component c : panel.labels) {
                c.setForeground(Color.DARK_GRAY);
            }
        }
        return panel;
    }
        
    private void adjustColors(Color bg, Color fg) {
        for (Component c : panel.toAdjust) {
            c.setForeground(fg);
            c.setBackground(bg);
        }
    }

    private int getRowCount() {
        return 2;
    }
    
    private class RendererPanel extends JPanel {
        private final JLabel dateLabel;
        // NOTE: If you look at the original MailMan source you'll see I wrote
        // this code to deal with an arbitrary number of rows. I stuck with
        // 2 as that worked the best. Look to the original code for how this
        // is dealt with.
        private final JLabel labels[];
        private final JLabel fromLabel;
        private final JImagePanel imagePanel;
        private final JLabel subjectLabel;
        private final List<Component> toAdjust;
        private final char[] tmpChars;
        private String text;
        private int layoutWidth;

        RendererPanel() {
            tmpChars = new char[512];
            setBorder(new EmptyBorder(4, 4, 4, 4));
            setOpaque(true);
            fromLabel = new JLabel(" ");
            fromLabel.setForeground(FROM_COLOR);
            fromLabel.setFont(fromLabel.getFont().deriveFont(Font.ITALIC));
            subjectLabel = new JLabel("a");
            dateLabel = new JLabel("a");
            dateLabel.setFont(dateLabel.getFont().deriveFont(Font.BOLD));
            Font f = subjectLabel.getFont();
            subjectLabel.setFont(f.deriveFont(Font.BOLD, f.getSize() + 4f));
            labels = new JLabel[getRowCount()];
            for (int i = 0; i < getRowCount(); i++) {
                labels[i] = new JLabel(" ");
            }
            imagePanel = new JImagePanel();
            imagePanel.setBorder(new LineBorder(Color.BLACK, 1));
            imagePanel.setEditable(false);
            
            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);
            layout.setAutoCreateGaps(true);
            
            // Horizontal group
            GroupLayout.SequentialGroup hg = layout.createSequentialGroup();
            layout.setHorizontalGroup(hg);
            // Add the image panel with a fixed size
            hg.addComponent(imagePanel, IS, IS, IS).
               // Create parallel group that will contain the rows
               addGroup(layout.createParallelGroup().
                 // First row contains the subject, and date labels
                 // Notice the subject is infinitely resizable
                 addGroup(layout.createSequentialGroup().
                   addComponent(subjectLabel, 1, 1, Integer.MAX_VALUE).
                   addComponent(dateLabel)).
                 // Second row is a single label that is infinitely resizable.
                 addComponent(labels[0], 1, 1, Integer.MAX_VALUE).
                 // Third row contains two labels: text and from. The label
                 // is infinited resizable.
                 addGroup(layout.createSequentialGroup().
                   addComponent(labels[1], 1, 1, Integer.MAX_VALUE).
                   addComponent(fromLabel)));

            GroupLayout.ParallelGroup vg = layout.createParallelGroup();
            layout.setVerticalGroup(vg);
            // Add the image panel at a fixed size.
            vg.addComponent(imagePanel, GroupLayout.Alignment.CENTER, IS, IS, IS).
               // Then a sequential group for the rows
               addGroup(layout.createSequentialGroup().
                 // For row contains subject and date, baseline aligned.
                 addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                   addComponent(subjectLabel).
                   addComponent(dateLabel)).
                 // Then a gap. The gap is used to avoid GroupLayout generating
                 // padding for us. You typically want the padding, but in this
                 // case we want a tight layout, and 0 fits the bill nicely.
                 addGap(0).
                 // Second row is a single label
                 addComponent(labels[0]).
                 addGap(0).
                 // And last row contains two labels, baseline aligned.
                 addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                   addComponent(labels[1]).
                   addComponent(fromLabel)));
            
            toAdjust = new LinkedList<Component>();
            toAdjust.add(dateLabel);
            toAdjust.addAll(Arrays.asList(labels));
            toAdjust.add(this);
            toAdjust.add(fromLabel);
        }
        
        public void setMessage(Conversa conversa) {
            if (conversa == null) {
                text = null;
                subjectLabel.setText(" ");
                fromLabel.setText(" ");
                resetMessageLabels();
            } else {
                subjectLabel.setText(conversa.getTitulo());
                PessoaFisica pessoa = conversa.getPessoa();
                
                if (pessoa != null && pessoa.getFoto() != null) {
                    imagePanel.setImagePath(pessoa.getFoto());
                } else imagePanel.setImagePath(null);
                
                text = conversa.getConteudo();
                reflowText();
                dateLabel.setText(DateHelper.convert(conversa.getData().getTime()));
                if (conversa.getPessoa() != null) fromLabel.setText(conversa.getPessoa().getNome());
                else fromLabel.setText(" ");
             }
        }
        
        public void doLayout() {
            super.doLayout();
            if (layoutWidth != getWidth()) {
                layoutWidth = getWidth();
                reflowText();
            }
        }
        
        private int nextNonWhitespace(int index, int length) {
            while (index < length && Character.isWhitespace(tmpChars[index])) {
                tmpChars[index] = ' ';
                index++;
            }
            return index;
        }
        
        private int nextWhitespace(int index, int length) {
            while (index < length && !Character.isWhitespace(tmpChars[index])) {
                index++;
            }
            return index;
        }

        private void resetMessageLabels() {
            for (JLabel label : labels) {
                label.setText(" ");
            }
        }
        
        private void reflowText() {
            // This obviously isn't i18n aware; it was written for a demo.
            resetMessageLabels();
            int availableWidth = labels[0].getWidth();
            if (availableWidth > 0 && text != null) {
                int charCount = Math.min(tmpChars.length, text.length());
                text.getChars(0, charCount, tmpChars, 0);
                FontMetrics fm = labels[0].getFontMetrics(labels[0].getFont());
                int lineStart = nextNonWhitespace(0, charCount);
                int lastChunkThatFits = lineStart;
                int lineIndex = 0;
                int index = lineStart;
                boolean tooLong = false;
                while (!tooLong && index < charCount && lineIndex < labels.length) {
                    index = nextWhitespace(index, charCount);
                    int charWidth = fm.charsWidth(tmpChars, lineStart, index - lineStart);
                    if (charWidth < availableWidth) {
                        lastChunkThatFits = index;
                        index = nextNonWhitespace(index, charCount);
                    } else if (lastChunkThatFits == lineStart) {
                        tooLong = true;
                    } else {
                        // charWidth > availableWidth, lastChunkThatFits != lineStart
                        labels[lineIndex++].setText(new String(
                                tmpChars, lineStart, lastChunkThatFits - lineStart));
                        lineStart = nextNonWhitespace(lastChunkThatFits, charCount);
                        lastChunkThatFits = lineStart;
                    }
                }
                if (!tooLong && lineIndex < labels.length && lineStart != charCount) {
                    labels[lineIndex].setText(new String(
                            tmpChars, lineStart, charCount - lineStart));
                }
            }
        }
    }
}
