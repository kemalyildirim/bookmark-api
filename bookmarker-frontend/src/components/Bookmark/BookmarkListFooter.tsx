"use client";

interface FooterProps {
  totalPages: number;
  totalElements?: number;
  currentPage: number;
  loadNewPage: Function;
}
const BookmarkListFooter = ({
  totalPages,
  totalElements,
  currentPage,
  loadNewPage,
}: FooterProps) => {
  return (
    <nav className="pagination justify-content-center mt-3" aria-label="...">
      <li className={`page-item ${currentPage <= 1 ? "disabled" : ""}`}>
        <a
          className="page-link"
          onClick={() => loadNewPage(currentPage - 1)}
          href="#"
        >
          Previous
        </a>
      </li>
      {Array.from({ length: totalPages }, (_, i: number) => {
        i++;
        return (
          <li
            className={`page-item ${currentPage == i ? "active" : ""}`}
            key={i}
          >
            <a className="page-link" onClick={() => loadNewPage(i)} href="#">
              {i}
            </a>
          </li>
        );
      })}
      <li
        className={`page-item ${currentPage >= totalPages ? "disabled" : ""}`}
      >
        <a
          className="page-link"
          onClick={() => loadNewPage(currentPage + 1)}
          href="#"
        >
          Next
        </a>
      </li>
    </nav>
  );
};

export default BookmarkListFooter;
