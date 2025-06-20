import java.util.List;
import java.util.Map;

public class SearchRequest {
    private String query;
    private Map<String, List<String>> filters;
    private String sortBy;
    private int page;
    private int pageSize;

    public SearchRequest(String query, Map<String, List<String>> filters, String sortBy, int page, int pageSize) {
        this.query = query;
        this.filters = filters;
        this.sortBy = sortBy;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getQuery() { return query; }
    public Map<String, List<String>> getFilters() { return filters; }
    public String getSortBy() { return sortBy; }
    public int getPage() { return page; }
    public int getPageSize() { return pageSize; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String query = "";
        private Map<String, List<String>> filters = new java.util.HashMap<>();
        private String sortBy = "relevance";
        private int page = 1;
        private int pageSize = 10;

        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public Builder addFilter(String filterType, String value) {
            this.filters.computeIfAbsent(filterType, k -> new java.util.ArrayList<>()).add(value);
            return this;
        }

        public Builder filters(Map<String, List<String>> filters) {
            this.filters = filters;
            return this;
        }

        public Builder sortBy(String sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public SearchRequest build() {
            return new SearchRequest(query, filters, sortBy, page, pageSize);
        }
    }
}