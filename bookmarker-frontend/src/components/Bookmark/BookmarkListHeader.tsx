const BookmarkListHeader = () => {
  return (
    <div className="input-group mb-5 row-4">
      <input
        type="text"
        className="form-control"
        aria-label="Default"
        aria-describedby="inputGroup-sizing-default"
      />
      <div className="input-group-prepend">
        <span className="input-group-text" id="inputGroup-sizing-default">
          Search
        </span>
      </div>
    </div>
  );
};

export default BookmarkListHeader;
