package ${packageName}.adapters;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.leo.lu.llrecyclerview.LLRecyclerViewAdapter;
import ${packageName}.items.BaseViewHolder;
import ${packageName}.items.ItemView;
import ${packageName}.listener.OttoBus;
import ${packageName}.model.PagerResult;
import ${packageName}.model.BaseModelJson;
import ${packageName}.rest.MyErrorHandler;
import ${packageName}.rest.MyRestClient;
import ${packageName}.tools.AndroidTool;


import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by leo on 2015/10/31.
 */
@EBean
public abstract class BaseUltimateRecyclerViewAdapter<T> extends LLRecyclerViewAdapter<BaseViewHolder> {
    private DynamicHeight dynamicHeight;
    public VerticalAndHorizontal verticalAndHorizontal;
    private List<T> items = new ArrayList<>();
    private int total = 0;
    private boolean isFirstOnly = true;
    private Interpolator mInterpolator = new LinearInterpolator();
    private OnItemClickListener<T> onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;

    @Bean
    OttoBus bus;

    @RootContext
    Context context;

    @Bean
    MyErrorHandler myErrorHandler;

    @RestService
    MyRestClient myRestClient;

    boolean isRefresh;


    @AfterInject
    void afterInject() {
        myRestClient.setRestErrorHandler(myErrorHandler);
    }

    /**
     * 获取更多的数据
     *
     * @param pageIndex
     * @param pageSize
     * @param objects
     */
    @Background
    public abstract void getMoreData(int pageIndex, int pageSize, boolean isRefresh, Object... objects);

    @UiThread
    protected void afterGetMoreData(BaseModelJson<PagerResult<T>> result) {
        if (result == null) {
            result = new BaseModelJson<>();
        } else if (result.Successful) {
            if (isRefresh) {
                clear();
            }
            setTotal(result.Data.RowCount);
            if (result.Data.ListData.size() > 0) {
                insertAll(result.Data.ListData, getItems().size());
            }
        } else {
            AndroidTool.showToast(context, result.Error);
        }
        if (bus != null) {
            bus.post(result);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
        if (getItemViewType(position) == BaseUltimateRecyclerViewAdapter.VIEW_TYPES.NORMAL) {
            ItemView<T> itemView = (ItemView) viewHolder.itemView;
            itemView.init(items.get(customHeaderView != null ? position - 1 : position), this, viewHolder);
            setNormalClick(viewHolder);
        } else if (getItemViewType(position) == VIEW_TYPES.HEADER) {
            setHeaderClick(viewHolder);
        } else if (getItemViewType(position) == VIEW_TYPES.FOOTER) {

        }
        if (!isFirstOnly) {
            for (Animator anim : getAdapterAnimations(viewHolder.itemView, AdapterAnimationType.ScaleIn)) {
                anim.setDuration(300).start();
                anim.setInterpolator(mInterpolator);
            }
        }
    }

    @Override
    public BaseViewHolder newFooterHolder(View view) {
        return new BaseViewHolder(view);
    }

    @Override
    public BaseViewHolder newHeaderHolder(View view) {
        return new BaseViewHolder(view);
    }

    /**
     * requirement: ADVIEW
     *
     * @param view v
     * @return holder for this ADVIEW
     */
    public BaseViewHolder getAdViewHolder(View view) {
        return null;
    }

    /**
     * requirement: CUSTOMVIEW
     *
     * @param view v
     * @return v
     */
    public BaseViewHolder newCustomViewHolder(View view) {
        return null;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        final View view = onCreateItemView(parent);
        // //修正 item不充满
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new BaseViewHolder(view);
    }


    /**
     * 设置 ItemView
     *
     * @param parent
     * @return
     */
    protected abstract View onCreateItemView(ViewGroup parent);

    /**
     * allow resource layout id to be introduced
     *
     * @param mLayout res id
     */
    public void setCustomLoadMoreView(Context context, @LayoutRes int mLayout) {
        View h_layout = LayoutInflater.from(context).inflate(mLayout, null);
        setCustomLoadMoreView(h_layout);
    }

    /**
     * 设置普通itemclick事件
     *
     * @param viewHolder
     */
    private void setNormalClick(final BaseViewHolder viewHolder) {
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder, items.get(customHeaderView != null ? viewHolder.getAdapterPosition() - 1 : viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
                }
            });
        }
        if (onItemLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(viewHolder, items.get(customHeaderView != null ? viewHolder.getAdapterPosition() - 1 : viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    /**
     * 设置头部点击事件
     *
     * @param viewHolder
     */
    private void setHeaderClick(final BaseViewHolder viewHolder) {

        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onHeaderClick(viewHolder, viewHolder.getAdapterPosition());
                }
            });
        }
        if (onItemLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onHeaderLongClick(viewHolder, viewHolder.getAdapterPosition());
                    return false;
                }
            });
        }
    }


    /**
     * allow resource layout id to be introduced
     *
     * @param mLayout res id
     */
    public void setCustomLoadMoreView(@LayoutRes int mLayout) {
//        View h_layout = LayoutInflater.from(context).inflate(mLayout, null);
//        setCustomLoadMoreView(h_layout);
    }


    @Override
    public int getAdapterItemCount() {
        return items.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return position;
    }

    /**
     * set adapter item click
     *
     * @param onItemClickListener onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void insertAll(List<T> list, int position) {
        items.addAll(position, list);
        if (customHeaderView != null) position++;
        notifyItemInserted(position);
    }

    /**
     * Clear the list of the adapter
     */
    public void clear() {
        int size = items.size();
        items.clear();
        total = 0;
        notifyItemRangeRemoved(customHeaderView != null ? 1 : 0, size);
    }

    public enum VerticalAndHorizontal {
        Vertical,
        Horizontal
    }

    public interface OnItemClickListener<T> {

        void onItemClick(RecyclerView.ViewHolder viewHolder, T obj, int position);

        void onHeaderClick(RecyclerView.ViewHolder viewHolder, int position);

    }

    public interface OnItemLongClickListener<T> {

        void onItemLongClick(RecyclerView.ViewHolder viewHolder, T obj, int position);

        void onHeaderLongClick(RecyclerView.ViewHolder viewHolder, int position);

    }

    @Override
    public long getItemId(int position) {
        // return position; <- this is not stable!

        // should returns stable value. IDs have to be kept the same value
        // even after its position has been changed.
        return position;
    }

    public interface DynamicHeight {
        void HeightChange(int position, int height);
    }

    public DynamicHeight getDynamicHeight() {
        return dynamicHeight;
    }

    public void setDynamicHeight(DynamicHeight dynamicHeight) {
        this.dynamicHeight = dynamicHeight;
    }
}
