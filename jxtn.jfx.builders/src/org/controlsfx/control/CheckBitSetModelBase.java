/**
 * Copyright (c) 2013, ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.controlsfx.control;

import java.util.BitSet;
import java.util.Map;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

import com.sun.javafx.collections.MappingChange;
import com.sun.javafx.collections.NonIterableChange;
import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;

// not public API
@SuppressWarnings("all")
abstract class CheckBitSetModelBase<T> extends MultipleSelectionModel<T>
{

    /***********************************************************************
     * * Internal properties * *
     **********************************************************************/

    private final Map<T, BooleanProperty> itemBooleanMap;

    private final BitSet selectedIndices;
    private final ReadOnlyUnbackedObservableList<Integer> selectedIndicesList;
    private final ReadOnlyUnbackedObservableList<T> selectedItemsList;

    /***********************************************************************
     * * Constructors * *
     **********************************************************************/

    CheckBitSetModelBase(final Map<T, BooleanProperty> itemBooleanMap)
    {
        this.itemBooleanMap = itemBooleanMap;

        this.selectedIndices = new BitSet();

        this.selectedIndicesList = new ReadOnlyUnbackedObservableList<Integer>()
            {
                @Override
                public Integer get(int index)
                {
                    if (index < 0 || index >= CheckBitSetModelBase.this.getItemCount())
                        return -1;

                    for (int pos = 0, val = CheckBitSetModelBase.this.selectedIndices.nextSetBit(0);
                    val >= 0 || pos == index;
                    pos++, val = CheckBitSetModelBase.this.selectedIndices.nextSetBit(val + 1))
                    {
                        if (pos == index)
                            return val;
                    }

                    return -1;
                }

                @Override
                public int size()
                {
                    return CheckBitSetModelBase.this.selectedIndices.cardinality();
                }

                @Override
                @SuppressWarnings("cast")
                public boolean contains(Object o)
                {
                    if (o instanceof Number)
                    {
                        Number n = (Number) o;
                        int index = n.intValue();

                        return index >= 0 && index < CheckBitSetModelBase.this.selectedIndices.length() &&
                                CheckBitSetModelBase.this.selectedIndices.get(index);
                    }

                    return false;
                }
            };

        this.selectedItemsList = new ReadOnlyUnbackedObservableList<T>()
            {
                @Override
                public T get(int i)
                {
                    int pos = CheckBitSetModelBase.this.selectedIndicesList.get(i);
                    if (pos < 0 || pos >= CheckBitSetModelBase.this.getItemCount())
                        return null;
                    return CheckBitSetModelBase.this.getItem(pos);
                }

                @Override
                public int size()
                {
                    return CheckBitSetModelBase.this.selectedIndices.cardinality();
                }
            };

        final MappingChange.Map<Integer, T> map = new MappingChange.Map<Integer, T>()
            {
                @Override
                public T map(Integer f)
                {
                    return CheckBitSetModelBase.this.getItem(f);
                }
            };
        this.selectedIndicesList.addListener(new ListChangeListener<Integer>()
            {
                @Override
                public void onChanged(final Change<? extends Integer> c)
                {
                    // when the selectedIndices ObservableList changes, we manually call
                    // the observers of the selectedItems ObservableList.
                    CheckBitSetModelBase.this.selectedItemsList.callObservers(new MappingChange<Integer, T>(c, map,
                            CheckBitSetModelBase.this.selectedItemsList));
                    c.reset();
                }
            });

        // this code is to handle the situation where a developer is manually
        // toggling the check model, and expecting the UI to update (without
        // this it won't happen!).
        this.getSelectedItems().addListener(new ListChangeListener<T>()
            {
                @Override
                public void onChanged(ListChangeListener.Change<? extends T> c)
                {
                    while (c.next())
                    {
                        if (c.wasAdded())
                        {
                            for (T item : c.getAddedSubList())
                            {
                                BooleanProperty p = CheckBitSetModelBase.this.getItemBooleanProperty(item);
                                if (p != null)
                                {
                                    p.set(true);
                                }
                            }
                        }

                        if (c.wasRemoved())
                        {
                            for (T item : c.getRemoved())
                            {
                                BooleanProperty p = CheckBitSetModelBase.this.getItemBooleanProperty(item);
                                if (p != null)
                                {
                                    p.set(false);
                                }
                            }
                        }
                    }
                }
            });
    }

    /***********************************************************************
     * * Abstract API * *
     **********************************************************************/

    public abstract T getItem(int index);

    public abstract int getItemCount();

    public abstract int getItemIndex(T item);

    BooleanProperty getItemBooleanProperty(T item)
    {
        return this.itemBooleanMap.get(item);
    }

    /***********************************************************************
     * * Public selection API * *
     **********************************************************************/

    /**
     * Returns a read-only list of the currently selected indices in the CheckBox.
     */
    @Override
    public ObservableList<Integer> getSelectedIndices()
    {
        return this.selectedIndicesList;
    }

    /**
     * Returns a read-only list of the currently selected items in the CheckBox.
     */
    @Override
    public ObservableList<T> getSelectedItems()
    {
        return this.selectedItemsList;
    }

    /** {@inheritDoc} */
    @Override
    public void selectAll()
    {
        for (int i = 0; i < this.getItemCount(); i++)
        {
            this.select(i);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void selectFirst()
    {
        this.select(0);
    }

    /** {@inheritDoc} */
    @Override
    public void selectIndices(int index, int... indices)
    {
        this.select(index);
        for (int i = 0; i < indices.length; i++)
        {
            this.select(indices[i]);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void selectLast()
    {
        this.select(this.getItemCount() - 1);
    }

    /** {@inheritDoc} */
    @Override
    public void clearAndSelect(int index)
    {
        this.clearSelection();
        this.select(index);
    }

    /** {@inheritDoc} */
    @Override
    public void clearSelection()
    {
        for (int index = 0; index < this.selectedIndices.length(); index++)
        {
            this.clearSelection(index);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clearSelection(int index)
    {
        if (index < 0 || index >= this.getItemCount())
            return;
        this.selectedIndices.clear(index);

        final int changeIndex = this.selectedIndicesList.indexOf(index);
        this.selectedIndicesList.callObservers(new NonIterableChange.SimpleRemovedChange<Integer>(changeIndex,
                changeIndex + 1, index, this.selectedIndicesList));
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty()
    {
        return this.selectedIndices.isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSelected(int index)
    {
        return this.selectedIndices.get(index);
    }

    /** {@inheritDoc} */
    @Override
    public void select(int index)
    {
        if (index < 0 || index >= this.getItemCount())
            return;
        this.selectedIndices.set(index);

        final int changeIndex = this.selectedIndicesList.indexOf(index);
        this.selectedIndicesList.callObservers(new NonIterableChange.SimpleAddChange<Integer>(changeIndex,
                changeIndex + 1, this.selectedIndicesList));
    }

    /** {@inheritDoc} */
    @Override
    public void select(T item)
    {
        int index = this.getItemIndex(item);
        this.select(index);
    }

    /** {@inheritDoc} */
    @Override
    public void selectNext()
    {
        // no-op
    }

    /** {@inheritDoc} */
    @Override
    public void selectPrevious()
    {
        // no-op
    }

    /***********************************************************************
     * * Private implementation * *
     **********************************************************************/

    protected void updateMap()
    {
        // reset the map
        this.itemBooleanMap.clear();
        for (int i = 0; i < this.getItemCount(); i++)
        {
            final int index = i;
            final T item = this.getItem(index);

            final BooleanProperty booleanProperty = new SimpleBooleanProperty(item, "selected", false); //$NON-NLS-1$
            this.itemBooleanMap.put(item, booleanProperty);

            // this is where we listen to changes to the boolean properties,
            // updating the selected indices list (and therefore indirectly
            // the selected items list) when the checkbox is toggled
            booleanProperty.addListener(new InvalidationListener()
                {
                    @Override
                    public void invalidated(Observable o)
                    {
                        final int changeIndex = CheckBitSetModelBase.this.selectedIndicesList.indexOf(index);

                        if (booleanProperty.get())
                        {
                            CheckBitSetModelBase.this.selectedIndices.set(index);
                            CheckBitSetModelBase.this.selectedIndicesList
                                    .callObservers(new NonIterableChange.SimpleAddChange<Integer>(changeIndex,
                                            changeIndex + 1, CheckBitSetModelBase.this.selectedIndicesList));
                        }
                        else
                        {
                            CheckBitSetModelBase.this.selectedIndices.clear(index);
                            CheckBitSetModelBase.this.selectedIndicesList
                                    .callObservers(new NonIterableChange.SimpleRemovedChange<Integer>(changeIndex,
                                            changeIndex, index, CheckBitSetModelBase.this.selectedIndicesList));
                        }
                    }
                });
        }
    }
}
